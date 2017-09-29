/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AdressePostaleDetailComponent } from '../../../../../../main/webapp/app/entities/adresse-postale/adresse-postale-detail.component';
import { AdressePostaleService } from '../../../../../../main/webapp/app/entities/adresse-postale/adresse-postale.service';
import { AdressePostale } from '../../../../../../main/webapp/app/entities/adresse-postale/adresse-postale.model';

describe('Component Tests', () => {

    describe('AdressePostale Management Detail Component', () => {
        let comp: AdressePostaleDetailComponent;
        let fixture: ComponentFixture<AdressePostaleDetailComponent>;
        let service: AdressePostaleService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [AdressePostaleDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AdressePostaleService,
                    JhiEventManager
                ]
            }).overrideTemplate(AdressePostaleDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AdressePostaleDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdressePostaleService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AdressePostale(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.adressePostale).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
