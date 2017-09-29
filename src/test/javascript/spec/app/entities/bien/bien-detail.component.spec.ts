/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { BienDetailComponent } from '../../../../../../main/webapp/app/entities/bien/bien-detail.component';
import { BienService } from '../../../../../../main/webapp/app/entities/bien/bien.service';
import { Bien } from '../../../../../../main/webapp/app/entities/bien/bien.model';

describe('Component Tests', () => {

    describe('Bien Management Detail Component', () => {
        let comp: BienDetailComponent;
        let fixture: ComponentFixture<BienDetailComponent>;
        let service: BienService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [BienDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    BienService,
                    JhiEventManager
                ]
            }).overrideTemplate(BienDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BienDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BienService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Bien(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.bien).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
