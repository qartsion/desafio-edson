import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BeneficioService, Beneficio } from './services/beneficio.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, FormsModule], // só módulos e diretivas
  templateUrl: './app.html',
  styleUrl: './app.css'
})


export class App implements OnInit 
{
  title = signal('frontend-app');

  beneficios = signal<Beneficio[]>([]);
  mensagem = signal('');
  fromId = 0;
  toId = 0;
  amount = 0;

  constructor(private beneficioService: BeneficioService) {}

    ngOnInit(): void 
    {
      this.beneficioService.listAll().subscribe({
        next: (data: Beneficio[]) => this.beneficios.set(data),
        error: (err) => {
          console.error('Erro ao carregar benefícios', err);
          this.mensagem.set('Erro ao carregar benefícios');
        }
      });
    }


    transferir(): void {
        if (!this.fromId || !this.toId || !this.amount || this.amount <= 0) {
          this.mensagem.set('Preencha todos os campos corretamente');
          return;
        }

        this.beneficioService.transfer(this.fromId, this.toId, this.amount).subscribe({
          next: (msg: string) => this.mensagem.set(msg),
          error: (err) => {
            // Mostra status e mensagem do backend
            console.error('Erro na transferência:', err);

            if (err.error && typeof err.error === 'string') {
              // Se o backend devolveu texto simples
              this.mensagem.set(`Erro: ${err.error}`);
            } else if (err.error && err.error.message) {
              // Se devolveu JSON com campo "message"
              this.mensagem.set(`Erro: ${err.error.message}`);
            } else {
              // Fallback genérico
              this.mensagem.set(`Erro na transferência (status ${err.status})`);
            }
          }
        });
    }


}
