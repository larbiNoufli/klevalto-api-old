/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PoleDetailComponent } from '../../../../../../main/webapp/app/entities/pole/pole-detail.component';
import { PoleService } from '../../../../../../main/webapp/app/entities/pole/pole.service';
import { Pole } from '../../../../../../main/webapp/app/entities/pole/pole.model';

describe('Component Tests', () => {

    describe('Pole Management Detail Component', () => {
        let comp: PoleDetailComponent;
        let fixture: ComponentFixture<PoleDetailComponent>;
        let service: PoleService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [PoleDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PoleService,
                    JhiEventManager
                ]
            }).overrideTemplate(PoleDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PoleDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PoleService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Pole(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.pole).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
