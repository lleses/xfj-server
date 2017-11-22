//汉语,数字,字母
function fmName(obj) {
	obj.value = obj.value.replace(/[^{\u2E80-\u9FFF}^\w\-——·，。！!_（）()【】“”、]/g, '');
	return obj.value;
}
//汉语,数字,字母
function vfmName(value) {
	return value.replace(/[^{\u2E80-\u9FFF}^\w\-——·，。！!_（）()【】“”、]/g, '');
}

/** 过滤非数字字符 */
function fmNum(obj) {
	obj.value = obj.value.replace(/[^\d]/g, '');
	return obj.value;
}