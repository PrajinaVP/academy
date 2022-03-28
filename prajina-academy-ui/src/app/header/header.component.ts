import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  menuItems = [
    {linkId: 1, linkName: 'Home', linkUrl:'home'},
    {linkId: 2, linkName: 'Course', linkUrl:'course'},
    {linkId:3, linkName: 'Module', linkUrl:'module'}
    
  ]
  title: string = 'Prajina Academy';
  collapsed = true;
  @Output() tabSelected = new EventEmitter<string>();
  constructor() { }

  ngOnInit(): void {
  }

  onSelect(tab: string) {
    this.tabSelected.emit(tab);
  }

}
