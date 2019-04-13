import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { DetailComponent } from './detail/detail.component';
import { SendComponent } from './send/send.component';
import { EvaluateComponent } from './evaluate/evaluate.component';

const routes: Routes = [
  { path: 'tabs', loadChildren: './tabs/tabs.module#TabsPageModule' },
  { path: '', loadChildren: './login/login.module#LoginPageModule' },
  { path: 'chat/:otherId', loadChildren: './chat/chat.module#ChatPageModule' },
  { path: 'detail', component: DetailComponent },
  { path: 'send', component: SendComponent },
  { path: 'evaluate', component: EvaluateComponent }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
