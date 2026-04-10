import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Beneficio {
  id: number;
  nome: string;
  descricao: string;
  valor: number;
  ativo: boolean;
}

@Injectable({ providedIn: 'root' })
export class BeneficioService 
{
  private apiUrlGet = '/backend-module/api/beneficios';
  private apiUrlTransfer = '/backend-module/api/beneficios/transfer';

  constructor(private http: HttpClient) {}

  listAll(): Observable<Beneficio[]> 
  {
    return this.http.get<Beneficio[]>(this.apiUrlGet);
  }

  /*
  // Angular enviando como json
  transfer(fromId: number, toId: number, amount: number): Observable<string> 
  {
    const payload = { fromId, toId, amount };
    return this.http.post(this.apiUrlTransfer, payload, { responseType: 'text' });
  }
  */
  
    transfer(fromId: number, toId: number, amount: number): Observable<string> {
        const body = new HttpParams()
          .set('fromId', fromId.toString())
          .set('toId', toId.toString())
          .set('amount', amount.toString());

        const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

        return this.http.post(this.apiUrlTransfer, body.toString(), { headers, responseType: 'text' });
    }
  
  
}
