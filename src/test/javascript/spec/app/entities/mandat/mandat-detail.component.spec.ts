/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { MandatDetailComponent } from '../../../../../../main/webapp/app/entities/mandat/mandat-detail.component';
import { MandatService } from '../../../../../../main/webapp/app/entities/mandat/mandat.service';
import { Mandat } from '../../../../../../main/webapp/app/entities/mandat/mandat.model';

describe('Component Tests', () => {

    describe('Mandat Management Detail Component', () => {
        let comp: MandatDetailComponent;
        let fixture: ComponentFixture<MandatDetailComponent>;
        let service: MandatService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [MandatDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    MandatService,
                    JhiEventManager
                ]
            }).overrideTemplate(MandatDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MandatDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MandatService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Mandat(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.mandat).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
