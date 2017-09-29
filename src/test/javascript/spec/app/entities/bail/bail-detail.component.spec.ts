/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { BailDetailComponent } from '../../../../../../main/webapp/app/entities/bail/bail-detail.component';
import { BailService } from '../../../../../../main/webapp/app/entities/bail/bail.service';
import { Bail } from '../../../../../../main/webapp/app/entities/bail/bail.model';

describe('Component Tests', () => {

    describe('Bail Management Detail Component', () => {
        let comp: BailDetailComponent;
        let fixture: ComponentFixture<BailDetailComponent>;
        let service: BailService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [BailDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    BailService,
                    JhiEventManager
                ]
            }).overrideTemplate(BailDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BailDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BailService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Bail(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.bail).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
