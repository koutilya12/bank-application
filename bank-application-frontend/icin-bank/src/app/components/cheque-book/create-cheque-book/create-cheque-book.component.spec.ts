import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateChequeBookComponent } from './create-cheque-book.component';

describe('CreateChequeBookComponent', () => {
  let component: CreateChequeBookComponent;
  let fixture: ComponentFixture<CreateChequeBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateChequeBookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateChequeBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
