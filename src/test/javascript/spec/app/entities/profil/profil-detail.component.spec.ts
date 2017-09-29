/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SergicTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProfilDetailComponent } from '../../../../../../main/webapp/app/entities/profil/profil-detail.component';
import { ProfilService } from '../../../../../../main/webapp/app/entities/profil/profil.service';
import { Profil } from '../../../../../../main/webapp/app/entities/profil/profil.model';

describe('Component Tests', () => {

    describe('Profil Management Detail Component', () => {
        let comp: ProfilDetailComponent;
        let fixture: ComponentFixture<ProfilDetailComponent>;
        let service: ProfilService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SergicTestModule],
                declarations: [ProfilDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProfilService,
                    JhiEventManager
                ]
            }).overrideTemplate(ProfilDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProfilDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Profil(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.profil).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
