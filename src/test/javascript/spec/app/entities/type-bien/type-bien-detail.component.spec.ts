/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TypeBienDetailComponent } from '../../../../../../main/webapp/app/entities/type-bien/type-bien-detail.component';
import { TypeBienService } from '../../../../../../main/webapp/app/entities/type-bien/type-bien.service';
import { TypeBien } from '../../../../../../main/webapp/app/entities/type-bien/type-bien.model';

describe('Component Tests', () => {

    describe('TypeBien Management Detail Component', () => {
        let comp: TypeBienDetailComponent;
        let fixture: ComponentFixture<TypeBienDetailComponent>;
        let service: TypeBienService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [TypeBienDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TypeBienService,
                    JhiEventManager
                ]
            }).overrideTemplate(TypeBienDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TypeBienDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeBienService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new TypeBien(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.typeBien).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
