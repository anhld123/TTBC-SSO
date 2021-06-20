function initBranchParams(){
    var branchObject = $('#cboBranchInfo');
    if(branchObject.length > 0){                    
        $.getJSON('loadBranchAction', {branchCode: '', impClassName: getImpClassValue(), procedureName: getProcedureValue('#cboBranchInfoProc')}, function (jsonResponse) {                        
            branchObject.find('option').remove();
            $.each(jsonResponse.branchList, function (key, value) {
                $('<option>').val(key).text(value).appendTo(branchObject);
            });                        
            initPosParams($("#cboBranchInfo option:selected").val());
        });                    
    }
};

//Load pos info when select branch
$(document).ready(function () {
    $('#cboBranchInfo').change(function (event) {                    
        var branchValue = $("select#cboBranchInfo").val();                     
        initPosParams(branchValue);
    });
});

function initPosParams(branchValue){                
    var posObject = $('#cboPosInfo');                
    if(posObject.length > 0){                                                          
        $.getJSON('loadPosAction', {branchCode: branchValue, impClassName: getImpClassValue(), procedureName: getProcedureValue('#cboPosInfoProc')}, function (jsonResponse) {                                                                                                
            posObject.find('option').remove();
            $.each(jsonResponse.posList, function (key, value) {
                $('<option>').val(key).text(value).appendTo(posObject);
            });
            initCommuneParams($("#cboPosInfo option:selected").val());
        });
    }
};                        

//Load commune info when select pos
$(document).ready(function () {
    $('#cboPosInfo').change(function (event) {
        var posValue = $("select#cboPosInfo").val();                    
        initCommuneParams(posValue);
    });
});

function initCommuneParams(posValue){ 
    var communeObject = $('#cboCommuneInfo');
    if(communeObject.length > 0){                        
        $.getJSON('loadCommuneAction', {posCode: posValue, impClassName: getImpClassValue(), procedureName: getProcedureValue('#cboCommuneInfoProc')}, function (jsonResponse) {                                                                                                
            communeObject.find('option').remove();
            $.each(jsonResponse.communeList, function (key, value) {
                $('<option>').val(key).text(value).appendTo(communeObject);
            });
            initGroupParams($("#cboCommuneInfo option:selected").val());                        
        });
    }
};

//Load group info when select commune
$(document).ready(function () {
    $('#cboCommuneInfo').change(function (event) {
        var communeValue = $("select#cboCommuneInfo").val(); 
        initGroupParams(communeValue);                                        
    });
});

function initGroupParams(communeValue){ 
    var groupObject = $('#cboGroupInfo');
    if(groupObject.length > 0){                        
        $.getJSON('loadGroupAction', {communeCode: communeValue, impClassName: getImpClassValue(), procedureName: getProcedureValue('#cboGroupInfoProc')}, function (jsonResponse) {                                                                                                
            groupObject.find('option').remove();
            $.each(jsonResponse.groupList, function (key, value) {
                $('<option>').val(key).text(value).appendTo(groupObject);
            });
        });
    }
};

function getImpClassValue(){
    var impClassValue = $('#impDaoImpClassName');
    return (impClassValue.length === 0 ? '' : impClassValue.val());
};

function getProcedureValue(procedureId){
    var procedureValue = $(procedureId); 
    return (procedureValue.length === 0 ? '' : procedureValue.val());
};

