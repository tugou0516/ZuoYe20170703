jQuery.fn.extend({
    // jQuery的表单数据转换为JSON的插件
    // 使用语法：$('#form-id').form@Json();
    form2Json: function() {
        var formData = {};
        var inputs = this.serializeArray();
        $.each(inputs,function(index,input){
            formData[input.name] = input.value;
        });
        var dataJson = JSON.stringify(formData);
        return dataJson;
    }
});