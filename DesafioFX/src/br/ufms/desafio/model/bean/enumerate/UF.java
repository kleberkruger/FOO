/*
 * Copyright (C) 2016 kleberkruger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ufms.desafio.model.bean.enumerate;

/**
 *
 * @author kleberkruger
 */
public enum UF {

    AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR,
    PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;

    public int getId() {
        switch (this) {
            case AC:
                return 1;
            case AL:
                return 2;
            case AP:
                return 3;
            case AM:
                return 4;
            case BA:
                return 5;
            case CE:
                return 6;
            case DF:
                return 7;
            case ES:
                return 8;
            case GO:
                return 9;
            case MA:
                return 10;
            case MT:
                return 11;
            case MS:
                return 12;
            case MG:
                return 13;
            case PA:
                return 14;
            case PB:
                return 15;
            case PR:
                return 16;
            case PE:
                return 17;
            case PI:
                return 18;
            case RJ:
                return 19;
            case RN:
                return 20;
            case RS:
                return 21;
            case RO:
                return 22;
            case RR:
                return 23;
            case SC:
                return 24;
            case SP:
                return 25;
            case SE:
                return 26;
            case TO:
                return 27;
            default:
                return 0;
        }
    }

    public String getCapital() {
        switch (this) {
            case AC:
                return "Rio Branco";
            case AL:
                return "Maceió";
            case AP:
                return "Macapá";
            case AM:
                return "Manaus";
            case BA:
                return "Salvador";
            case CE:
                return "Fortaleza";
            case DF:
                return "Brasília";
            case ES:
                return "Vitória";
            case GO:
                return "Goiânia";
            case MA:
                return "São Luís";
            case MT:
                return "Ciuabá";
            case MS:
                return "Campo Grande";
            case MG:
                return "Belo Horizonte";
            case PA:
                return "Belém";
            case PB:
                return "João Pessoa";
            case PR:
                return "Curitiba";
            case PE:
                return "Recife";
            case PI:
                return "Teresina";
            case RJ:
                return "Rio de Janeiro";
            case RN:
                return "Natal";
            case RS:
                return "Porto Alegre";
            case RO:
                return "Porto Velho";
            case RR:
                return "Boa Vista";
            case SC:
                return "Florianópolis";
            case SP:
                return "São Paulo";
            case SE:
                return "Aracaju";
            case TO:
                return "Palmas";
            default:
                return null;
        }
    }

    public String getNomeEstado() {
        switch (this) {
            case AC:
                return "Acre";
            case AL:
                return "Alagoas";
            case AP:
                return "Amapá";
            case AM:
                return "Amazonas";
            case BA:
                return "Bahia";
            case CE:
                return "Ceará";
            case DF:
                return "Distrito Federal";
            case ES:
                return "Espírito Santo";
            case GO:
                return "Goiás";
            case MA:
                return "Maranhão";
            case MT:
                return "Mato Grosso";
            case MS:
                return "Mato Grosso do Sul";
            case MG:
                return "Minas Gerais";
            case PA:
                return "Pará";
            case PB:
                return "Paraíba";
            case PR:
                return "Paraná";
            case PE:
                return "Pernambuco";
            case PI:
                return "Piauí";
            case RJ:
                return "Rio de Janeiro";
            case RN:
                return "Rio Grande do Norte";
            case RS:
                return "Rio Grande do Sul";
            case RO:
                return "Rondônia";
            case RR:
                return "Roraima";
            case SC:
                return "Santa Catarina";
            case SP:
                return "São Paulo";
            case SE:
                return "Sergipe";
            case TO:
                return "Tocantins";
            default:
                return null;
        }
    }

}
