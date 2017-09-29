/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { UtilisationRibTiersDetailComponent } from '../../../../../../main/webapp/app/entities/utilisation-rib-tiers/utilisation-rib-tiers-detail.component';
import { UtilisationRibTiersService } from '../../../../../../main/webapp/app/entities/utilisation-rib-tiers/utilisation-rib-tiers.service';
import { UtilisationRibTiers } from '../../../../../../main/webapp/app/entities/utilisation-rib-tiers/utilisation-rib-tiers.model';

describe('Component Tests', () => {

    describe('UtilisationRibTiers Management Detail Component', () => {
        let comp: UtilisationRibTiersDetailComponent;
        let fixture: ComponentFixture<UtilisationRibTiersDetailComponent>;
        let service: UtilisationRibTiersService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [UtilisationRibTiersDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    UtilisationRibTiersService,
                    JhiEventManager
                ]
            }).overrideTemplate(UtilisationRibTiersDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UtilisationRibTiersDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisationRibTiersService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new UtilisationRibTiers(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.utilisationRibTiers).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
