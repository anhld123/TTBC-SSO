/*
 * Nguyễn Phú Vinh (0849.358.358) - 11/09/2020
 * Xử lý báo cáo 
 */

$(document).ready(function() {
	
	// Định nghĩ danh mục
	var PRIDPGD =["PARA_MAPGD"];
	var PRIDXA =["PARA_MAXA","PV_MAXA"];
	
    /* Danh mục POS -> Xa */
    $("#PARA_MAPGD").change(function() {
        func_reload_dm("PARA_MAPGD",PRIDXA,2,4,0,4);
    });
    
    /* Hàm thực reload danh mục*/
    function func_reload_dm(idelm,idels,str1,end1,str2,end2){
		var var1,var2;
		var1 = $("[id=" + idelm + "] option:selected").text().substr(str1, end1);
        $.each(idels, function(index, value ) {
            if( $("[id=" + value + "]").length ){
			$("[id=" + value + "]").children().remove().end();
			$("[id=" + value + "_DATA] > option").each(function() {
				var2 = $(this).text().substr(str2, end2);
				if(var1.trim() == var2.trim()){
					$("[id=" + value + "]").prepend("<option value='" + $(this).val() + "'> " + $(this).text() + " </option>");
				}
			});
			$("[id=" + value + "]").prepend("<option value='000000' selected='selected'>--Tất cả---</option>");
            }
        });
    }
});