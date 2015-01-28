(function ($) {

    var QuoteWebApp = {
        checkExistElement: function($element){
            return $element.length > 0;
        },

        getQueryParams: function () {
            var match,
                pl = /\+/g,  // Regex for replacing addition symbol with a space
                search = /([^&=]+)=?([^&]*)/g,
                decode = function (s) {
                    return decodeURIComponent(s.replace(pl, " "));
                },
                query = window.location.search.substring(1),
                urlParams = {};

            while (match = search.exec(query)) {
                urlParams[decode(match[1])] = decode(match[2]);
            }

            return urlParams;
        },

        redirectByUrl: function(url){
            window.location.href = url;
        },

        redirectByQueryParams: function(queryParams){
            var queryParam = "",
                redirectUrl = "";

            $.each(queryParams, function(key, value) {
                if(key && value){
                    queryParam += "&" + key + "=" + value;
                }
            });

            if(queryParam){
                redirectUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + "?" + queryParam.substring(1);
                QuoteWebApp.redirectByUrl(redirectUrl);
            }
        },

        copyUserName: function () {
            var $source         = $("#userList"),
                $destination    = $("#userName");

            if(QuoteWebApp.checkExistElement($source) && QuoteWebApp.checkExistElement($destination)){
                $source.on("change", function () {
                    var $selectedOption = $(this).find("option:selected"),
                        selectedOptionValue = $.trim($selectedOption.val()),
                        selectedOptionText = $.trim($selectedOption.text());

                    if (selectedOptionValue && selectedOptionText) {
                        $source.blur();
                        $destination.val(selectedOptionText);
                        $destination.focus();
                    }
                });
            }
        },

        applyNavigation: function () {
            var $navigation = $(".b-quote-navigation-wrap select");

            if(QuoteWebApp.checkExistElement($navigation)){
                $navigation.on("change", function () {
                    var $this = $(this),
                        $selectedOption     = $this.find("option:selected"),
                        paramCode           = $.trim($selectedOption.attr("data-param-code")),
                        paramValue          = $.trim($selectedOption.attr("data-param-value")),
                        queryParams         = QuoteWebApp.getQueryParams();

                    if(paramCode && paramValue){
                        queryParams[paramCode] = paramValue;
                        QuoteWebApp.redirectByQueryParams(queryParams);
                    }
                });
            }
        },

        applyPagination: function(){
            var pagination = $(".b-quote-list-pagination a");

            if(QuoteWebApp.checkExistElement(pagination)){
                pagination.on("click", function(e){
                    e.preventDefault();

                    var $this               = $(this),
                        $parent             = $this.closest("li"),
                        paramCode           = $.trim($this.attr("data-param-code")),
                        paramValue          = $.trim($this.attr("data-param-value")),
                        queryParams         = QuoteWebApp.getQueryParams();

                    if(paramCode && paramValue){
                        if($parent.hasClass("disabled") || $parent.hasClass("active")){
                            //Nothing
                        } else {
                            queryParams[paramCode] = paramValue;
                            QuoteWebApp.redirectByQueryParams(queryParams);
                        }
                    }
                });
            }
        }
    };

    $(document).ready(function () {
        QuoteWebApp.copyUserName();
        QuoteWebApp.applyNavigation();
        QuoteWebApp.applyPagination();
    });

})(jQuery);