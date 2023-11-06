(function ($, $document) {
  "use strict";

  $document.on("dialog-ready", function () {
    var dialog = $(".cq-dialog");
 console.log("Js is Called");
    var parentCheck = dialog.find("coral-checkbox[name='./checkBox']");
    var headingCheckBox = dialog.find("coral-checkbox[name='./heading']");
    var messageCheckBox = dialog.find("coral-checkbox[name='./message']");
    function updateVisibility() {
     console.log("Hello updateVisibility");
      if (parentCheck.prop("checked")) {
        headingCheckBox.show();
        messageCheckBox.show();
      } else {
        headingCheckBox.hide();
        messageCheckBox.hide();
      }
    }
    updateVisibility();
    parentCheck.on("change", function () {
      updateVisibility();
    });
  });
})(Granite.$, jQuery(document));
