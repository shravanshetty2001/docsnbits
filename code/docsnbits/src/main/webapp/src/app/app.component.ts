import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  currentFile?: File;
  progress = 0;
  message = '';
  title = 'docsnbits-ui';

  mainMatForm!: FormGroup;
  ckEditorConfig: any = { toolbar: [
    ['Source', 'Templates', 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'CopyFormatting', 'RemoveFormat'],
    [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ],
    [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ],
    [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl' ],
    [ 'Link', 'Unlink', 'Anchor' ],
    [ 'Image', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ],
    [ 'Styles', 'Format', 'Font', 'FontSize' ],
    [ 'TextColor', 'BGColor' ],
    [ 'Maximize', 'ShowBlocks' ]
  ] };

  fileName = 'Upload PDF';
  showEditorWindow: boolean = false;
  xmlData: string = '';

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.setupMainMatForm();
  }

  setupMainMatForm(): boolean {
    const result = false;
    this.mainMatForm = this.createMainMatForm();
    return result;
  }

  createMainMatForm(): FormGroup {
    let result = null;
    const formBuilderItem: any = {};

    formBuilderItem['ckeditor_text'] = ['Type Here...'];

    result = this.formBuilder.group(formBuilderItem);
    return result;
  }

  onMainMatFormNgSubmit(event: any): boolean {
    const result = true;
    console.log('*** onMainMatFormNgSubmit ***');
    console.log(event);
    return result;
  }

  onCkEditorReady(editor: any): boolean {
    const result = true;
    console.log('*** onCkEditorReady ***');
    console.log(editor);
    return result;
  }

  selectFile(event: any): void {
    console.log('*** selectFile ***');
    console.log(event);
    if (event.target.files && event.target.files[0]) {
      const file: File = event.target.files[0];
      this.currentFile = file;
      this.fileName = this.currentFile.name;
      console.log(this.currentFile, 'currentFile');
      const reader = new FileReader();

      reader.onload = (e) => {
        this.xmlData = e.target?.result as string;
      };
      reader.readAsText(this.currentFile);
    } else {
      this.fileName = 'Upload PDF';
    }
  }

  upload(): void {
    if (this.fileName !== 'Upload PDF') {
      this.showEditorWindow = true;
    }
  }

  downloadAsPDF() {
    
  }
}