/**
 * @license Copyright (c) 2003-2021, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
    config.language = 'ko';
    config.toolbarCanCollapse = true;
    /*
    config.toolbarGroups = [
        { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'forms', groups: [ 'forms' ] },
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
        { name: 'links', groups: [ 'links' ] },
        { name: 'insert', groups: [ 'insert' ] },
        '/',
        { name: 'styles', groups: [ 'styles' ] },
        { name: 'colors', groups: [ 'colors' ] },
        { name: 'tools', groups: [ 'tools' ] },
        { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
        { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
        { name: 'others', groups: [ 'others' ] },
        { name: 'about', groups: [ 'about' ] }
    ];
     */

    config.removeButtons = 'Source,Save,NewPage,Preview,Print,Templates,PasteFromWord,Scayt,SelectAll,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CreateDiv,Language,Anchor,Unlink,Link,Image,Flash,PageBreak,Iframe,Maximize,About';
    //config.removeButtons = 'Source,Save,NewPage,Preview,Print,Templates,PasteFromWord,Scayt,SelectAll,Form,Checkbox,Radio,TextField,Textarea,Select,Button,HiddenField,CreateDiv,Language,Anchor,Unlink,Link,Flash,PageBreak,Iframe,Maximize,About';

    config.fontSize_defaultLabel = '12';
    config.enterMode='2';
    config.font_defaultLabel = 'Pretendard'
    config.font_names = 'Pretendard; 돋움; 맑은 고딕; 바탕; 궁서;' + CKEDITOR.config.font_names;
    //config.extraAllowedContent = 'img[width,height]';
    config.allowedContent=true
};
