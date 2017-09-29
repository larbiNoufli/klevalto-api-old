/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { RibTiersDetailComponent } from '../../../../../../main/webapp/app/entities/rib-tiers/rib-tiers-detail.component';
import { RibTiersService } from '../../../../../../main/webapp/app/entities/rib-tiers/rib-tiers.service';
import { RibTiers } from '../../../../../../main/webapp/app/entities/rib-tiers/rib-tiers.model';

describe('Component Tests', () => {

    describe('RibTiers Management Detail Component', () => {
        let comp: RibTiersDetailComponent;
        let fixture: ComponentFixture<RibTiersDetailComponent>;
        let service: RibTiersService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [RibTiersDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    RibTiersService,
                    JhiEventManager
                ]
            }).overrideTemplate(RibTiersDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RibTiersDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RibTiersService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new RibTiers(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.ribTiers).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
