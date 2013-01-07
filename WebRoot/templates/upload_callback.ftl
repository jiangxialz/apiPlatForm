{
	"statusCode":"${dwzResponser.statusCode?default('300')}",
	"message":"${dwzResponser.message?default('上传失败...')}",
	"navTabId":"${dwzResponser.navTabId?default('')}",
	"rel":"${dwzResponser.rel?default('')}",
	"callbackType":"${dwzResponser.callbackType?default('closeCurrent')}",
	"forwardUrl":"${dwzResponser.forwardUrl?default('')}"
}