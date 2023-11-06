package com.smartstore.core.servlets;

import com.smartstore.core.been.MembersList;
import com.smartstore.core.constants.Constants;
import com.smartstore.core.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component(service = Servlet.class, property =
        {"sling.servlet.paths=/smartstore/generate/excel",
                "sling.servlet.methods=POST"})
public class ExcelGeneratorServlet extends SlingAllMethodsServlet {

    private final static Logger Log = LoggerFactory.getLogger(ExcelGeneratorServlet.class);

    private static final String fileName = "SmartStore_Members-List";

    private static final String USER_NAME = "Username";

    private static final String EMAIL = "Email";

    private static final String PASSWORD = "Password";

    private static final String CONTACT_NUMBER = "ContactNumber";

    private static final String ROOT_PATH = "/database/smartstore/members";

    private static final String REGISTRATION_DATE = "Registered Date";
    private static final String CSV_EXT = ".csv";

    private static final String EXCEL_EXT = ".xlsx";

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws ServletException, IOException {

        boolean isMemberChecked = Boolean.parseBoolean(request.getParameter("isMemberChecked"));

        // ... (rest of your code)

        // Send the checkbox value as a response
        response.getWriter().write(String.valueOf(isMemberChecked));

        String csvFileName = fileName + DateUtils.getDateStr(new Date(),
                Constants.FORMAT_ORIGINAL_DATE) +CSV_EXT;

        String excelFileName = fileName + DateUtils.getDateStr(new Date(),
                Constants.FORMAT_ORIGINAL_DATE) +EXCEL_EXT;

        if (csvFileName.endsWith(CSV_EXT)){
            response.setCharacterEncoding(Constants.SJIS);
            response.setHeader("Content-Type", "text/csv;charset=SJIS");
            response.setHeader("Content-Disposition", "attachment;filename=" + checkCSVFileName(fileName));
        } else if (excelFileName.endsWith(EXCEL_EXT)) {
            response.setCharacterEncoding(Constants.SJIS);
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=SJIS");
            response.setHeader("Content-Disposition", "attachment;filename=" + checkExcelFileName(fileName));
        }


        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            Resource dataResource = resourceResolver.getResource(ROOT_PATH);
            List<MembersList> dataObjects = getSmartStoreMembers(dataResource, request);
            writeCsv(dataObjects, response.getWriter());
        } catch (IOException | ResourceNotFoundException e) {
            Log.error("CSV download error:", e);
        }
    }

    private String checkCSVFileName(final String fileName) {
        if (StringUtils.containsIgnoreCase(fileName, CSV_EXT)) {
            return fileName;
        }
        return fileName + CSV_EXT;
    }

    private String checkExcelFileName(final String fileName) {
        if (StringUtils.containsIgnoreCase(fileName, EXCEL_EXT)) {
            return fileName;
        }
        return fileName + EXCEL_EXT;
    }

    private void writeCsv(List<MembersList> csv, PrintWriter writer) throws IOException {
        String[] header = {"SNO", "UserName", "Email", "Password", "RegisterDate", "ContactNumber"};
        writeCsvLine(writer, header);

        int sno = 1;
        for (MembersList model : csv) {
            String[] csvData = {
                    Integer.toString(sno),
                    model.getUserName(),
                    model.getEmail(),
                    model.getPassword(),
                    model.getRegisterDate(),
                    model.getContactNumber()
            };
            writeCsvLine(writer, csvData);
            sno++;
        }
        writer.flush();
    }

    private void writeCsvLine(PrintWriter writer, String[] csvData) throws IOException {
        for (int i = 0; i < csvData.length; i++) {
            String field = StringUtils.defaultString(csvData[i]);

            if (field.contains(",")) {
                field = '"' + field + '"';
            }

            if (i < (csvData.length - 1)) {
                field = field.concat(",");
            }

            writer.append(field);
        }
        writer.println();
    }

    private List<MembersList> getSmartStoreMembers(Resource resource, SlingHttpServletRequest request) {
        List<MembersList> members = new ArrayList<>();

        ResourceResolver resourceResolver = request.getResourceResolver();
        String path = resource.getPath();
        Resource resourcePath = resourceResolver.getResource(path);
        for (Resource childResource : resourcePath.getChildren()) {
            if (ResourceUtil.isNonExistingResource(childResource)) {
                continue;
            }
            ValueMap properties = childResource.getValueMap();

            String userName = properties.get(USER_NAME, String.class);
            String email = properties.get(EMAIL, String.class);
            String password = properties.get(PASSWORD, String.class);
            String registerDate = properties.get(REGISTRATION_DATE, String.class);
            String contactNumber = properties.get(CONTACT_NUMBER, String.class);

            MembersList member = new MembersList(userName, email, password, registerDate, contactNumber);
            members.add(member);
        }
        return members;
    }
}