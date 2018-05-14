function open_edit_window(path,man_id,r_id) {
    var obj = new Object();
    obj.value=1;
    obj.name=2;
    window.showModalDialog(path+"/loadMan.do?id="+man_id+"&residence_id="+r_id, obj, "dialogHeight:600px;dialogWidth:1000px;status=no;");
    window.location.reload(); 
}

