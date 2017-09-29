/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { LocataireBienDetailComponent } from '../../../../../../main/webapp/app/entities/locataire-bien/locataire-bien-detail.component';
import { LocataireBienService } from '../../../../../../main/webapp/app/entities/locataire-bien/locataire-bien.service';
import { LocataireBien } from '../../../../../../main/webapp/app/entities/locataire-bien/locataire-bien.model';

describe('Component Tests', () => {

    describe('LocataireBien Management Detail Component', () => {
        let comp: LocataireBienDetailComponent;
        let fixture: ComponentFixture<LocataireBienDetailComponent>;
        let service: LocataireBienService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [LocataireBienDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    LocataireBienService,
                    JhiEventManager
                ]
            }).overrideTemplate(LocataireBienDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LocataireBienDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LocataireBienService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new LocataireBien(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.locataireBien).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
