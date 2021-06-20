  /**
         * 
         * @param {type} loai_api loai api module tu 01 -> 05
         * @param {type} typeFind 1 truy van theo ma kh, 2 truy van theo cmt, 3 truy van theo sdt
         * @param {type} custId ma khach hang
         * @param {type} groupId ma to, tai khoan casa
         * @param {type} loanId ma khoan vay
         * @returns {undefined}
         */
function hienthichitiet(loai_api, typeFind, custId, groupId, loanId, posCode) {
    var ht1 = screen.availHeight - 100;
    var wt1 = 1050;
    var left1 = (screen.width / 2) - (wt1 / 2);
    var top1 = 10;


    var url = "popup.action?loai_api=" + loai_api + "&typeFind=" + typeFind + "&custId=" + custId +
            "&groupId=" + groupId + "&loanId=" + loanId+"&posCode="+posCode;
    //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
    var popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
}