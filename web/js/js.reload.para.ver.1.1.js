/*
 * Nguyễn Phú Vinh (0849.358.358) - 03/03/2021
 * Xử lý báo cáo khi chọn đơn vị
 */

$(document).ready(function () {

    // Định nghĩ danh mục
    var PRIDPGD = ["PARA_MAPGD","PV_MAPGD","PARA_MAPGD_MAPGD","PV_POS_CD","PV_POS_CD_MAPGD","POS_CD","PARA_POS"];
    var PRIDXA = ["PARA_MAXA","PV_MAXA","PV_MAXAD","PARA_COMMUNEID"];
	var PRIDTHON = ["PARA_MATHON","PV_MATHON"];
	var PRIDTO = ["PARA_MATO","PV_MATO"];
	
    /* Danh mục POS -> Xã, thôn, tổ */
    $("#PARA_MAPGD").change(function () {
		func_check_element(PRIDPGD, PRIDXA, 2, 4, 2, 4, 6);
		func_check_element(PRIDPGD, PRIDTHON, 2, 4, 0, 4, 6);
		func_check_element(PRIDPGD, PRIDTO, 2, 4, 0, 4, 8);
    });
	
	 /* Danh mục POS -> Xã, thôn, tổ */
    $("#PARA_POS").change(function () {
		func_check_element(PRIDPGD, PRIDXA, 2, 4, 2, 4, 6);
		func_check_element(PRIDPGD, PRIDTHON, 2, 4, 0, 4, 6);
		func_check_element(PRIDPGD, PRIDTO, 2, 4, 0, 4, 8);
    });
	
	 /* Danh mục POS -> Xã, thôn, tổ */
    $("#PV_POS_CD_MAPGD").change(function () {
		func_check_element(PRIDPGD, PRIDXA, 2, 4, 2, 4, 6);
		func_check_element(PRIDPGD, PRIDTHON, 2, 4, 0, 4, 6);
		func_check_element(PRIDPGD, PRIDTO, 2, 4, 0, 4, 8);
    });
	
	
	/* Danh mục POS -> Xã, thôn, tổ */
    $("#POS_CD").change(function () {
		func_check_element(PRIDPGD, PRIDXA, 2, 4, 2, 4, 6);
		func_check_element(PRIDPGD, PRIDTHON, 2, 4, 0, 4, 6);
		func_check_element(PRIDPGD, PRIDTO, 2, 4, 0, 4, 8);
    });
	
	/* Danh mục POS -> Xã, thôn, tổ */
    $("#PV_POS_CD").change(function () {
		func_check_element(PRIDPGD, PRIDXA, 2, 4, 2, 4, 6);
		func_check_element(PRIDPGD, PRIDTHON, 2, 4, 0, 4, 6);
		func_check_element(PRIDPGD, PRIDTO, 2, 4, 0, 4, 8);
    });

	$("#PV_MAPGD").change(function () {
		func_check_element(PRIDPGD, PRIDXA, 2, 4, 2, 4, 6);
		func_check_element(PRIDPGD, PRIDTHON, 2, 4, 0, 4, 6);
		func_check_element(PRIDPGD, PRIDTO, 2, 4, 0, 4, 8);
    });
	
	/* Danh mục POS -> Xa, thôn, tổ */
    $("#PARA_MAPGD_MAPGD").change(function () {
		func_check_element(PRIDPGD, PRIDXA, 2, 4, 2, 4, 6);
		func_check_element(PRIDPGD, PRIDTHON, 2, 4, 0, 4, 6);
		func_check_element(PRIDPGD, PRIDTO, 2, 4, 0, 4, 8);
    });
	
	
	
	/* Danh mục xã -> thôn, tổ */
    $("#PARA_MAXA").change(function () {
		func_check_element(PRIDXA, PRIDTHON, 0, 7, 0, 6, 6);
		func_check_element(PRIDXA, PRIDTO, 0, 7, 0, 6, 8);
    });

	/* Danh mục xã -> thôn, tổ */
    $("#PV_MAXA").change(function () {
		func_check_element(PRIDXA, PRIDTHON, 0, 7, 0, 6, 6);
		func_check_element(PRIDXA, PRIDTO, 0, 7, 0, 6, 8);
    });
	
	/* Danh mục xã -> thôn, tổ */
    $("#PV_MAXAD").change(function () {
		func_check_element(PRIDXA, PRIDTHON, 0, 7, 0, 6, 6);
		func_check_element(PRIDXA, PRIDTO, 0, 7, 0, 6, 8);
    });

	
	/* Danh mục Thôn -> tổ */
    $("#PARA_MATHON").change(function () {
		func_check_element(PRIDTHON, PRIDTO, 0, 9, 0, 8, 8);
    });

	/* Danh mục Thôn -> tổ */
    $("#PV_MATHON").change(function () {
		func_check_element(PRIDTHON, PRIDTO, 0, 9, 0, 8, 8);
    });
	
	
	//Hàm kiểm tra đối tượng
    function func_check_element(paElement, subElment,isatrt1,iend1,isatrt2,iend2,strlen) {
		$.each(paElement, function (indexPA, value1PA) {
            if ($("[id=" + value1PA + "]").length) {
				$.each(subElment, function (indexSUB, valueSUB) {
					if ($("[id=" + valueSUB + "]").length) {
						func_clear_element(valueSUB);	
						func_reload_dm(value1PA, valueSUB, isatrt1,iend1,isatrt2,iend2,strlen);
						func_beautiful_element(valueSUB);
					}
				});
			}
        });
    }
	
    /* Hàm thực reload danh mục*/
    function func_reload_dm(idelm, idels, str1, end1, str2, end2, strlen) {
        var var1, var2;
		var1 = $("[id=" + idelm + "] option:selected").val().substr(str1, end1);
		//Kiểm tra trường hợp 000000
		if(var1.trim()=='000000'){
			if(PRIDXA.includes(idelm)){
				$.each(PRIDPGD, function (indexALL, valueALL) {
					if ($("[id=" + valueALL + "]").length) {
						$("[id=" + valueALL + "]").change();
						func_addall_element(idelm);
					}
				});
			}
			if(PRIDTHON.includes(idelm)){
				$.each(PRIDXA, function (indexALL, valueALL) {
					if ($("[id=" + valueALL + "]").length) {
						$("[id=" + valueALL + "]").change();
						func_addall_element(idelm);
					}
				});
			}
		}
		$("[id=" + idels + "_DATA] > option").each(function () {
			strst = $(this).text();
			var2 = strst.substr(strst.length-strlen,strlen).substr(str2, end2);
			if (var1.trim() == var2.trim()) {
				$("[id=" + idels + "]").prepend("<option value='" + $(this).val() + "'> " + strst.substr(0,strst.length - strlen) + " </option>");
			}
		});
    }

    //Hàm làm sạch dữ liệu
    function func_clear_element(rselment) {
		$("[id=" + rselment + "]").children().remove().end();
    }

    //Hàm làm đẹp dữ liệu
    function func_beautiful_element(rselment) {
		var options = $("[id=" + rselment + "] > option");        
		options.detach().sort(function(a,b) {
			var at = $(a).val();
			var bt = $(b).val();         
			return (at > bt)?1:((at < bt)?-1:0);
		});
		options.appendTo("[id=" + rselment + "]");
		$("[id=" + rselment + "]").prepend("<option value='000000' selected='selected'>------Tất cả-----</option>");
    }
	
	//Hàm thêm thuộc tính tất cả
	function func_addall_element(strELM){
		if(PRIDXA.include(strELM)){
			$.each(PRIDTHON, function (indexADD, valueADD) {
				if ($("[id=" + valueADD + "]").length) {
					$("[id=" + valueADD + "]").children('option[value="000000"]').remove();
					$("[id=" + valueADD + "]").prepend("<option value='000000' selected='selected'>------Tất cả-----</option>");
				}
			});
			$.each(PRIDTO, function (indexADD, valueADD) {
				if ($("[id=" + valueADD + "]").length) {
					$("[id=" + valueADD + "]").children('option[value="000000"]').remove();
					$("[id=" + valueADD + "]").prepend("<option value='000000' selected='selected'>------Tất cả-----</option>");
				}
			});
		}
		if(PRIDTHON.include(strELM)){
			$.each(PRIDTO, function (indexADD, valueADD) {
				if ($("[id=" + valueADD + "]").length) {
					$("[id=" + valueADD + "]").children('option[value="000000"]').remove();
					$("[id=" + valueADD + "]").prepend("<option value='000000' selected='selected'>------Tất cả-----</option>");
				}
			});
		}
	}
	
	$("#PARA_MAPGD").change();
	$("#PV_POS_CD").change();
	$("#PARA_POS").change();
	$("#POS_CD").change();
	$("#PV_MAPGD").change();
	$("#PARA_MAPGD_MAPGD").change();
	$("#PV_POS_CD_MAPGD").change();	

});