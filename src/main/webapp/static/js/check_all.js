function allCheckbox() {
	var trs = document.getElementById("tab").getElementsByTagName("tr");

	for (var i = 1; i < trs.length; i++) {
		document.write(trs[i].getElementsByTagName("td")[0].innerHTML);
	}

}

function allCheckboxOne() {
	var names = document.getElementsByName("checkbox");
	var allcheckbox = document.getElementById("allcheckbox");
	for (var i = 0; i < names.length; i++) {
		if (allcheckbox.checked == true) {
			names[i].checked = true;
		} else {
			names[i].checked = false;
		}
	}
}

/* 根据单个复选框的选择情况确定全选复选框是否被选中 */
function selectSingle() {
	var k = 0;
	var oInput = document.getElementsByName("checkbox");
	for (var i = 0; i < oInput.length; i++) {
		if (oInput[i].checked == false) {
			k = 1;
			break;
		}
	}
	if (k == 0) {
		document.getElementById("allcheckbox").checked = true;
	} else {
		document.getElementById("allcheckbox").checked = false;
	}
}

/**
 * 是否选中删除项
 */
function isSelectDeleteData(){
	var k = 0;
	var oInput = document.getElementsByName("checkbox");
	for (var i = 0; i < oInput.length; i++) {
		if (oInput[i].checked == true) {
			k = 1;
			break;
		}
	}
	return k;
}