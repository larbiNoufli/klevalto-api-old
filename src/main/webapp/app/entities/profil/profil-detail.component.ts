import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Profil } from './profil.model';
import { ProfilService } from './profil.service';

@Component({
    selector: 'jhi-profil-detail',
    templateUrl: './profil-detail.component.html'
})
export class ProfilDetailComponent implements OnInit, OnDestroy {

    profil: Profil;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private profilService: ProfilService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProfils();
    }

    load(id) {
        this.profilService.find(id).subscribe((profil) => {
            this.profil = profil;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProfils() {
        this.eventSubscriber = this.eventManager.subscribe(
            'profilListModification',
            (response) => this.load(this.profil.id)
        );
    }
}
