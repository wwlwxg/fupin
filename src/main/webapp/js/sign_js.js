document.write("<script src='js/common.js'></script>"); 

function open_edit_window(path,man_id,r_id) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    window.showModalDialog(path+"/loadMan.do?id="+man_id+"&residence_id="+r_id, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
    window.location.reload(); 
}

function open_dougang_hu_window(id) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    //window.showModalDialog("ProjectContractEditUIServlet?id=" + id + "&type=" + type + "&project_id=" + project_id, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
    //window.location.reload(); 
    openWin("addDougangHu.do?id=" + id, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
}

function open_dougang_man_window(id,huId) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    openWin("addDougangMan.do?id=" + id + "&huId="+huId, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
}

function open_dougang_study_window(id,manId) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    openWin("addStudy.do?id=" + id + "&manId="+manId, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
}

function open_dougang_work_window(id,manId) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    openWin("addWork.do?id=" + id + "&manId="+manId, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
}

function open_dougang_sick_window(id,manId) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    openWin("addSick.do?id=" + id + "&manId="+manId, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
}

function open_dougang_income_window(id,huId) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    openWin("addDougangIncome.do?id=" + id + "&huId="+huId, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
}

function open_dougang_soldier_window(id,manId) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    openWin("addSoldier.do?id=" + id + "&manId="+manId, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
}

