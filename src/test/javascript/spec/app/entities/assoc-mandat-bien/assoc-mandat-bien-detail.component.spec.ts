/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AssocMandatBienDetailComponent } from '../../../../../../main/webapp/app/entities/assoc-mandat-bien/assoc-mandat-bien-detail.component';
import { AssocMandatBienService } from '../../../../../../main/webapp/app/entities/assoc-mandat-bien/assoc-mandat-bien.service';
import { AssocMandatBien } from '../../../../../../main/webapp/app/entities/assoc-mandat-bien/assoc-mandat-bien.model';

describe('Component Tests', () => {

    describe('AssocMandatBien Management Detail Component', () => {
        let comp: AssocMandatBienDetailComponent;
        let fixture: ComponentFixture<AssocMandatBienDetailComponent>;
        let service: AssocMandatBienService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [AssocMandatBienDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AssocMandatBienService,
                    JhiEventManager
                ]
            }).overrideTemplate(AssocMandatBienDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AssocMandatBienDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssocMandatBienService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AssocMandatBien(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.assocMandatBien).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
