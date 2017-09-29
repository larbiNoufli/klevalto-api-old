/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PrelevementDetailComponent } from '../../../../../../main/webapp/app/entities/prelevement/prelevement-detail.component';
import { PrelevementService } from '../../../../../../main/webapp/app/entities/prelevement/prelevement.service';
import { Prelevement } from '../../../../../../main/webapp/app/entities/prelevement/prelevement.model';

describe('Component Tests', () => {

    describe('Prelevement Management Detail Component', () => {
        let comp: PrelevementDetailComponent;
        let fixture: ComponentFixture<PrelevementDetailComponent>;
        let service: PrelevementService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [PrelevementDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PrelevementService,
                    JhiEventManager
                ]
            }).overrideTemplate(PrelevementDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PrelevementDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PrelevementService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Prelevement(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.prelevement).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
