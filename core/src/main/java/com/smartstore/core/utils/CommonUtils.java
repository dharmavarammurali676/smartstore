package com.smartstore.core.utils;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.i18n.I18n;
import com.day.cq.wcm.api.Page;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

public class CommonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private CommonUtils() {
    }

    public static String getI18N(SlingHttpServletRequest request, String translateText) {
        Locale locale = request.getLocale();
        ResourceBundle resourceBundle = request.getResourceBundle(locale);
        I18n i18n = new I18n(resourceBundle);

        return i18n.get(translateText);
    }

    /**
     * Builds the data source from the data param, and adds to the request.
     *
     * @param request the request.
     * @param data    the data to create the datasource from
     * @param add
     */

    public static String replaceLineSeparator(String text, String replacement) {
        return StringUtils.defaultIfEmpty(StringUtils.replaceAll(text, "(\\r\\n|\\n)", replacement),
                StringUtils.EMPTY);
    }

    /**
     * Read file from repository as string
     *
     * @param path
     * @param resolver
     * @return
     */
    public static String readFileAsString(String path, ResourceResolver resolver) {
        try {
            return IOUtils.toString(readFile(path, resolver), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("[readFileAsString] Exception: ", e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * Read file from repository
     *
     * @param path
     * @param resourceResolver
     * @return
     */
    public static InputStream readFile(String path, ResourceResolver resolver) {
        Resource resource = resolver.getResource(path + "/jcr:content/renditions/original/jcr:content");
        ValueMap vm = resource.adaptTo(ValueMap.class);
        Objects.requireNonNull(vm);
        return vm.get(JcrConstants.JCR_DATA, InputStream.class);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }


    public static boolean checkTagContent(String[] articleTag, String[] tag) {
        if (tag == null) {
            return true;
        }
        List<String> listTag = Arrays.asList(tag);

        if (articleTag == null || articleTag.length == 0) {
            return false;
        }

        for (String t : articleTag) {
            // if Tag contain t, return true
            if (listTag.stream().anyMatch(o -> o.equals(t))) {
                return true;
            }
        }

        return false;
    }

    public static String getLocalePage(Page page) {
        if (Objects.isNull(page)) {
            return StringUtils.EMPTY;
        }
        if (page.getDepth() < 4) {
            return StringUtils.EMPTY;
        }
        return page.getPath().split("/")[4];
    }

    public static String getFormatPage(Page page) {
        if (Objects.isNull(page) || Objects.isNull(page.getProperties())) {
            return StringUtils.EMPTY;
        }
        return page.getProperties().get("dateFormat", "yyyy/MM/dd");
    }

    public static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            return value;
        }
    }

    public static Map<String, Object> sortByValue(Map<String, Object> unsortMap) {
        List<Map.Entry<String, Object>> list = new LinkedList<>(unsortMap.entrySet());

        try {
            Collections.sort(list, (o1, o2) -> ((String) o1.getValue()).compareTo((String) o2.getValue()));
        } catch (Exception ex) {
            LOGGER.error("[CommonUtils] sortByValue Error:", ex);
        }

        Map<String, Object> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
