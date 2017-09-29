/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AssocBailBienDetailComponent } from '../../../../../../main/webapp/app/entities/assoc-bail-bien/assoc-bail-bien-detail.component';
import { AssocBailBienService } from '../../../../../../main/webapp/app/entities/assoc-bail-bien/assoc-bail-bien.service';
import { AssocBailBien } from '../../../../../../main/webapp/app/entities/assoc-bail-bien/assoc-bail-bien.model';

describe('Component Tests', () => {

    describe('AssocBailBien Management Detail Component', () => {
        let comp: AssocBailBienDetailComponent;
        let fixture: ComponentFixture<AssocBailBienDetailComponent>;
        let service: AssocBailBienService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [AssocBailBienDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AssocBailBienService,
                    JhiEventManager
                ]
            }).overrideTemplate(AssocBailBienDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AssocBailBienDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssocBailBienService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AssocBailBien(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.assocBailBien).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
