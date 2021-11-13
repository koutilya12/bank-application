import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetChequeBookComponent } from './get-cheque-book.component';

describe('GetChequeBookComponent', () => {
  let component: GetChequeBookComponent;
  let fixture: ComponentFixture<GetChequeBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetChequeBookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetChequeBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
