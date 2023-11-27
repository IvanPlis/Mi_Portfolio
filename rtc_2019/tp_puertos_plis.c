/*
TP Ports I/O
Programacion UNSAM
Autor: Plis, Ivan
Año: 2019
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/io.h>
#include <math.h>
#include <stdbool.h>

#define P 0x70			//Posicion a leer

/*Funcion para obtener permiso de acceso a los puertos. Evito hacer muchos ioperm.*/
void getPerm(unsigned long port, unsigned long cant, int perm){
	if(ioperm(port,cant,perm)) {
		perror("ioperm");
		exit(-1);
	}
}


/*Leo un byte del registro rtc y recibo su numero*/
unsigned char in (unsigned char reg){
  	outb (reg, P);
  	return inb(P + 1);
}

/*Escribo un valor en el registro del RTC que envio como argumento*/
unsigned char out (unsigned char reg, unsigned char value){
	outb (reg, P);
	outb (value, P+1);
}

/*Chequea si hay una actualizacion del RTC en curso*/ 
void UIP(){
 	unsigned char regA;
 	regA=in(0x0A);
 	if((regA & 0x80)!=0){
		usleep(1984);			//Tiempo maximo de espera
	}
}

/*Seteo el registro A para tener un clock en 2Hz*/
void setClock(){
	getPerm(P, 2, 1);
	outb(0x0A, P);
	outb(0x2F, P+1);
}

/*Lee registros del RTC correspondientes a la hora*/
void showHora(){

	unsigned char seg,min,hora;
	UIP();
	seg=in(0x00);
	min=in(0x02);
	hora=in(0x04);
	printf("\n\t La hora actual es: %02x:%02x:%02x",hora,min,seg);

}

void showRowRTC(int i){
    switch (i){
        case 0: printf(" \n   |Segundos        "); break;
        case 1: printf(" \n   |Segundos Alarma "); break;
        case 2: printf(" \n   |Minutos         "); break;
        case 3: printf(" \n   |Minutos Alarma  "); break;
        case 4: printf(" \n   |Horas           "); break;
        case 5: printf(" \n   |Horas Alarma    "); break;
        case 6: printf(" \n   |Dia de la Semana"); break;
        case 7: printf(" \n   |Dia del Mes     "); break;
        case 8: printf(" \n   |Mes             "); break;
        case 9: printf(" \n   |Anio            "); break;
        case 10: printf(" \n   |Registro A      "); break;
        case 11: printf(" \n   |Registro B      "); break;
        case 12: printf(" \n   |Registro C      "); break;
        case 13: printf(" \n   |Registro D      "); break;
        default: break;
      }
 }


void showColRTC(){

	printf("\n\n\t\t   			 	 REGISTROS DEL RTC\n");
    printf("   ///////////////////////////////////////////////////////////////////////////////////////////////");
    printf("\n     Descripcion      |   Numero de Registro |    bits (BCD)    |   Hexa   | Min restantes hora \n");
    printf("   ///////////////////////////////////////////////////////////////////////////////////////////////");
   
}

/*Convierte a binario implementando el algoritmo de division y suma*/
int toBin(unsigned char dato){  
      
	int resto = 0, bin = 0;
	int i=1;

	while(dato > 0){  
		resto = dato % 2;                                                                                         
		dato = dato/2;                                          
		bin = bin + (resto*i);                       
		i = i*10; 
	}
	return bin;
}

void minLeftA(unsigned char minLeft){

	if(minLeft==0x5a){
		printf("|       60   ");
	}
	else if (minLeft==0x4a){
		printf("|       50   ");
	}
	else if (minLeft==0x3a){
		printf("|       40   ");
	}
	else if (minLeft==0x2a){
		printf("|       30   ");
	}
	else if (minLeft==0x1a){
		printf("|       20   ");
	}
	else if (minLeft==0x0a){
		printf("|       10   ");
	}
	else printf("|       %02x    ",minLeft);

}

void showDataRTC(){

	unsigned char dato;
	int i=0;
	int datoBin=0;

	getPerm(P, 2, 1);			//Permisos puertos 70 y 71
	UIP();						//Verifico si esta actualizando

	showColRTC();
	for (int i=0; i<14; i++){	//Hago un muestre de los puertos 0x00 a 0x0D
		dato=in(0x00+i);		
		datoBin=toBin(dato);
		showRowRTC(i);
		printf("  |   	%2d 	     |     %08d     |    %02x    " ,i, datoBin, dato);

		if(i == 2){
			unsigned char minLeft=90-dato;
			minLeftA(minLeft);
       } else printf("|");
	}
	getPerm (P,2,0);								//Retiro permisos
}

void ondaCuadrada (){

	unsigned char regC;
	printf("\n\n *** Onda Cuadrada f=2Hz ***\n\n");
	printf("0---+---1---+---2---+---3---+---4---+---5---+---6---+---7---+---8---+---9---+---10 \n");
	getPerm(P, 2, 1);		//Permisos port 70 y 71	
	
	UIP();
	unsigned char regB;		//Habilito interrupciones periodicas del RTC
	regB=in(0x0B);			//en el bit PIE del regB
	regB=(regB|0x40);

	out(0x0B, regB);		
	outb(0x0C, P);			//Espero interrupcion para empezar escala y pulso al mismo tiempo

	do {
		usleep(125000);	
		regC = inb(P+1);
		regC = regC & 0x40;	//Mascara con 0x40: 0100 0000 -> regC: 0?00 0000 
	 } while (regC == 0);
 
	for(int i=0;i<81;i++){
		UIP();
		regC=in(0x0C);          //Vuelvo a leer para borrar flags anteriores
		regC=regC & 0x40;

		if(regC != 0){			//Si el bit AF del regC esta en 1 y el resto en 0, genero un pulso
      		printf("|");       
      		fflush(stdout);
		}
		else{
      		printf("_");
      		fflush(stdout);
     	}

     usleep(125000);			//125000us para que me haga 8 muestreos por segundo
                      
    }
	getPerm (P,2,0);			//Retiro permisos
}


void setAlarma(){

	unsigned char regC;                                       
	unsigned int hora=0, min=0, seg=0;
	getPerm (P,2,1); 								//Accedo a los puertos
	
	printf("Ingrese hora, minutos y segundos de la alarma (HH:MM:SS) :\n");		//Pido hora de la alarma
	scanf("%02x", &hora);scanf("%02x", &min);scanf("%02x", &seg);				//y hago validacion
	while (hora>0x24 || min >0x59 || seg>0x59){									//Las pido como hex porque 
		printf("Alarma invalida. Ingrese nuevamente");							//su equivalente en binario
		scanf("%02x", &hora);scanf("%02x", &min);scanf("%02x", &seg);			//esta en BCD
	}

	out(0x0B, 0x30);				//Habilito bit de interrupcion por alarma del regB
	
	out(0x01, seg);					//Escribo los registros de la alarma
	out(0x03, min);
	out(0x05, hora);

	bool alarmaOn=true;
	printf ("\nEsperando alarma...\n");

	while(alarmaOn){			
		regC = in(0x0C);
  		if (regC & 0x20){ 					//Verifico Alarm Flag del regC. Si esta en 1 suena.
			showHora();
      		printf ("\t****RING RING ALARMA ACTIVADA****\n");
			alarmaOn=false;
     	}
		usleep (500000);
 	}

	getPerm (P,2,0);
 	printf ("\n");
}


void outParalelo(){

	getPerm(0x378, 3, 1);		//Permiso para los puertos 378h, 379h y 37Ah del PP
	getPerm(P, 2, 1);

	unsigned char hora;
	int horaBin;

	hora=~(in(0x04));			//Leo hora y la niego
	horaBin=toBin(hora);

	outb(hora, 0x378);
	printf ("Se envio al puerto paralelo : %02x | %08d \n", hora, horaBin);

	
	/*Transmision al otro extremo*/
	outb (0x00,0x37A);                 	//STROBE en 0. Byte en el Reg. de Datos
 	usleep (1);							//Tiempo min de STROBE
 	outb (0x01,0x37A); 					//STROBE en 1. Byte transmitido

	/*Supongo que estoy en el otro extremo*/
	hora=~hora;							//Los bytes llegan negados
	horaBin=toBin(hora);				//por lo que tengo el valor original

	printf("El otro extremo deberia recibir: %02X | %08d \n", hora, horaBin);

	getPerm(P, 2, 0);
	getPerm(0x378, 3, 0);
		
}

void caratula(){
	printf("\n------------------------------------\n");
	printf("Universidad Nacional de San Martin\n");
	printf("------------------------------------\n");
	printf("Trabajo practico de Programacion\n");
	printf("Alumno: Plis, Ivan\n");
	printf("Legajo: 9282\n");
	printf("2C 2019\n");
	printf("------------------------------------\n");
}

void menu(){
	
	printf("\n\nSeleccione una opcion:\n\n");
	printf("1- Mostrar registros 0x00 a 0x0D\n");
	printf("2- Mostrar onda cuadrada de 2Hz\n");
	printf("3- Programar alarma\n");
	printf("4- Enviar hora por el puerto paralelo\n");
	printf("5- Salir\n");

}

int main (){
	
	setClock();
	caratula();
	menu();
	int opc=0;
	scanf("%d", &opc);
	printf("\n");

	while (opc!=5){
		switch(opc){
			case 1:
				showDataRTC();
				break;
			case 2:
				ondaCuadrada();
				break;
			case 3:
				setAlarma();
				break;
			case 4:
				outParalelo();
				break;
			default:
				printf("Opcion invalida. Ingrese nuevamente\n");
				break;
		}
		menu();
		scanf("%d", &opc);
		printf("\n");
	}

	return 1;
}

