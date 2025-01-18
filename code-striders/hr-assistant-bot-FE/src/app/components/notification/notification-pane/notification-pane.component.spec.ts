import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationPaneComponent } from './notification-pane.component';

describe('NotificationPaneComponent', () => {
  let component: NotificationPaneComponent;
  let fixture: ComponentFixture<NotificationPaneComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NotificationPaneComponent]
    });
    fixture = TestBed.createComponent(NotificationPaneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
