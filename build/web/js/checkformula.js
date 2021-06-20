/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

            $(document).ready(function () {
                //Load dữ liệu từ server về và tr
                $("#process").click(function (e) {
                    var url = "checkrow.action";
                    var table = document.getElementsByTagName("table")[0];
                    var cells = table.getElementsByTagName("td");
                    var data2 = "";
                    var vale, cellIndex, rowIndex,elem;
                    // Thực hiện lấy dòng và cột để đưa xuống Java
                    elem = document.getElementsByName("txtvalue");
                    for (var i = 0; i < cells.length; i++) {
                        vale = elem[i].value;
                        data2 = data2 + i + ",";
                    }
                    $.ajax({
                        url: url,
                        data: {"data": data2},
                        dataType: 'json',
                        type: 'POST',
                        success: function (res) {
                            $('#info').html(res.resu);
                            elem = document.getElementsByName("txtvalue");
                            alert(res.resu);
                            elem[res.resu].style.color = "red";
                        }
                    });
                });
            });
