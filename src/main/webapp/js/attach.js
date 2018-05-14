function _add() {
		var tb = document.getElementById("tb");
		//写入一行
		var tr = tb.insertRow();
		//写入列
		var td = tr.insertCell();
		//写入数据
		td.innerHTML = "File：";
		//再声明一个新的td
		var td2 = tr.insertCell();
		//写入一个input
		td2.innerHTML = '<input type="file" name="sign_file"/><button onclick="_del(this);">删除</button>';
	}
	function _del(btn) {
		var tr = btn.parentNode.parentNode;
		//alert(tr.tagName);
		//获取tr在table中的下标
		var index = tr.rowIndex;
		//删除
		var tb = document.getElementById("tb");
		tb.deleteRow(index);
	}
	function _submit() {
		//遍历所的有文件
		var files = document.getElementsByName("sign_file");
		if (files.length == 0) {
			alert("没有可以上传的文件");
			return false;
		}
		for (var i = 0; i < files.length; i++) {
			if (files[i].value == "") {
				alert("第" + (i + 1) + "个文件不能为空");
				return false;
			}
		}
		document.forms['xx'].submit();
	}
	
	function _submit(is_verify) {
		//遍历所的有文件
		var files = document.getElementsByName("sign_file");
		if(is_verify) {
			alert("对不起，已经审核的签不允许修改，请联系小邓(*^__^*)");
			return false;
		}
		if (files.length == 0) {
			alert("没有可以上传的文件");
			return false;
		}
		for (var i = 0; i < files.length; i++) {
			if (files[i].value == "") {
				alert("第" + (i + 1) + "个文件不能为空");
				return false;
			}
		}
		document.forms['xx'].submit();
	}