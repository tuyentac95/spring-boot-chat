let emoji_btn = document.querySelector('.fa-smile');
let text_input = document.querySelector('.type_msg');
let picker = new EmojiButton({
    position: 'top-start'
});

picker.on('emoji',function (emoji){
    text_input.value += emoji;
});

emoji_btn.addEventListener('click',function (){
    picker.pickerVisible ? picker.hidePicker() : picker.showPicker(text_input);
})