DATAS SEGMENT

   STRING DB 'PLEASE INPUT A NUMBER YOU WANT TO CALCULATE:',0DH,0AH,'$'
   ERROR DB 0DH,0AH,' THE NUMBER IS TOO BIG! ',0DH,0AH,'$'
   OUTSTR DB 0DH,0AH,'RESULT IS : $'
   	ASK_1 DB 13,10,'Do you want to calculate the factorial of a number?','$'
	ASK_2 DB 13,10,'Please input Y/N:','$'
	ASK_3 DB 13,10,'Do you want to calculate the number of a number again ?','$'
	ANS DB 13,10,'The character is wrong, please input again','$'
   A DW 10000 DUP (0),0
   ANUM DW 1
   CARRY DW 0

	 infor1 db  "                welocom you to come here listeng!", '$'
	
	 mus_freg dw 262,262,262,294,262
		  dw 392,440,330,330,294
		  dw 247,247,247,262,247
		  dw 330,392,247,294,262
		  dw 220,247,220,247,220
		  dw 330,349,262,220,247
		  dw 196,262,294,294
		  dw 349,330,330,294,262,294
		  dw 330,262,262,262,294,262
		  dw 392,440,330,330,294
		  dw 294,247,247,247,262,247
		  dw 330,392,247,294,262
		  dw 220,247,220,247,220
		  dw 330,349,220,262,247
		  dw 247,330,392,294,262
		  dw -1

	 mus_time  dw 25,25,25,25,25
		  dw 25,100,25,25,100
		  dw 25,25,25,25,25
		  dw 25,100,25,25,100
		  dw 25,25,25,25,25
		  dw 25,100,25,25,75
		  dw 25,50,50,25
		  dw 50,25,25,25,50,25
		  dw 75,25,25,25,25,25
		  dw 25,100,25,25,25
		  dw 75,25,25,25,25,25
		  dw 25,100,25,25,100
		  dw 25,25,25,25,25
		  dw 25,100,25,25,25
		  dw 100,50,25,50,75


	WELCOME 	DB  0DH, 0AH, '   +-----------------------------------+', 0DH, 0AH
				DB '   |                    	       |', 0DH, 0AH
				DB '   |                     	       |', 0DH, 0AH			   
				DB '   |    Welcome to project 2 of ZWR    |', 0DH, 0AH
				DB '   |                      	       |', 0DH, 0AH
				DB '   |                    	       |', 0DH, 0AH
				DB '   +-----------------------------------+', 0DH, 0AH
				DB '            | | |         ', 0DH, 0AH
				DB '            | | | ____________       ', 0DH, 0AH
				DB '            | | |/            \  ', 0DH, 0AH
				DB '      +-------------------+    \     ', 0DH, 0AH
				DB '     / ................../    /|\   ', 0DH, 0AH
				DB '    /.....===.........../     \_/ ', 0DH, 0AH
				DB '   +-------------------+         ', 0DH, 0AH
				DB '$'
   
DATAS ENDS

stack segment stack
      db 900 dup(?)
stack ends

SHOWBM MACRO b
     LEA DX,b
     MOV AH,9
     INT 21H
ENDM
 
ADDRESS MACRO A,B
     LEA SI,A
     LEA BP,DS:B
ENDM


CODES SEGMENT
    ASSUME CS:CODES,ss:stack,DS:DATAS
START:
	
     mov ax, DATAS
     mov ds, ax
     mov ax, stack
     mov ss, ax
     mov sp, 200
    LEA DX,infor1
   	MOV AH,09H
   	INT 21H

     address mus_freg, mus_time
     call music

 
exit:     
     jmp START1
;------------����-------------
gensound proc near
     push ax
     push bx
     push cx
     push dx
     push di
 
     mov al, 0b6H
     out 43h, al
     mov dx, 12h
     mov ax, 348ch
     div di
     out 42h, al
 
     mov al, ah
     out 42h, al
 
     in al, 61h
     mov ah, al
     or al, 3
     out 61h, al
wait1:
     mov cx, 3314
     call waitf
delay1:
     dec bx
     jnz wait1
 
     mov al, ah
     out 61h, al
 
     pop di
     pop dx
     pop cx
     pop bx
     pop ax
     ret 
gensound endp
 
;--------------------------
waitf proc near
      push ax
waitf1:
      in al,61h
      and al,10h
      cmp al,ah
      je waitf1
      mov ah,al
      loop waitf1
      pop ax
      ret
waitf endp
;--------------�������ú���----------------
music proc near
      xor ax, ax
freg:
      mov di, [si]
      cmp di, 0FFFFH
      je end_mus
      mov bx, ds:[bp]
      call gensound
      add si, 2
      add bp, 2
      jmp freg
end_mus:
      ret
music endp
    

START1:

MOV 	AH, 	9             	;�����¼�ɹ�����ʾ
      	MOV 	DX, 	OFFSET WELCOME
      	INT 	21H
	
	MOV AH,08H
	INT 21H
	
	MOV AH,07H
	INT 21H
	
	MOV AX,3
	INT 10H
	
 	MOV AX,0
	MOV BX,0
	MOV CX,0
	MOV DX,0
    MOV AX,DATAS
    MOV DS,AX
  	mov ah,0  
  	  	
   	
ASK:    
	LEA DX,ASK_1
   	MOV AH,09H
   	INT 21H
   	
In1:	LEA DX,ASK_2
   	MOV AH,09H
   	INT 21H
   	
   	MOV AH,01H      ;�������벢��ʾ���ַ�����AL��
   	INT 21H
   	   	
   	CMP AL,'Y'      ;�Ƚϸ�������ַ���Y�ַ��������ת��BEGIN_1��
   	JE BEGIN_1
   	
   	CMP AL,'N'      ;�Ƚϸ�������ַ���N�ַ��������ת��END_0�У���������ת��ASK������
   	JE END_0   	
   	
WRONG:	LEA DX,ANS
   	MOV AH,09H
   	INT 21H
   	JMP In1   	
   	

    
BEGIN_1:   
	mov cx,10000
	LEA SI,A
Init:
	MOV AX,0
	MOV [SI],AX
	INC SI
	LOOP Init

	MOV DL,0DH
    MOV AH,2H
    INT 21h
    MOV DL,0AH
    MOV AH,2H
    INT 21h
    
	 MOV AX,DATAS
    MOV DS,AX
    MOV DX,OFFSET STRING
    MOV AH,09H
    INT 21H
    CALL SHURU
    CMP BP,2000
    JBE MOK
    MOV DX,OFFSET ERROR
    MOV AH,9
    INT 21H
    JMP BEGIN_1

MOK:
	CALL FACTORIAL
	CALL SHUCHU
    JMP OVER

OVER:
	MOV AH,08H
	INT 21H

	MOV AH,07H
	INT 21H
	MOV AX,3
	INT 10H
	
	LEA DX,ASK_3
   	MOV AH,09H
   	INT 21H
   	JMP In1

SHURU PROC
	PUSH DX
	PUSH CX
	PUSH BX
	PUSH AX
	XOR BP,BP
	MOV BX,10
	MOV CX,5 ;��ȡ�ַ���Ŀ
INPUT:
	MOV AH,0
	INT 16H
	CMP AL,0DH
	JZ OK
	CMP AL,'0'
	JB INPUT
	CMP AL,'9'
	JA INPUT
	MOV AH,0EH
	INT 10H
	AND AX,000FH ;�������λ
	XCHG AX,BP
	MUL BX 
	ADD BP,AX ;�˷�ָ���������ֳ��� 10��Ȼ�󽫳˻��� BP �Ĵ�����ӣ�������洢�� BP
	LOOP INPUT
OK:	NOP
	POP AX
	POP BX
	POP CX
	POP DX
	RET
SHURU ENDP

FACTORIAL PROC
	PUSH DX
	PUSH CX
	PUSH BX
	PUSH AX
	MOV AX,BP ;BP�Ĵ�����ֵ����AX�Ĵ���������1���бȽ�
	CMP BP,1
	JNZ BEGIN ;���BP������1������ת��BEGIN��ǩ��ִ�еݹ����
	MOV A[0],AX
	POP AX
	POP BX
	POP CX
	POP DX
	RET
BEGIN:
	DEC BP
	CALL FACTORIAL ;��BP�Ĵ�����ֵ��1������������ĵݹ����
	MOV BX,1
AMUL:
	PUSH AX
	PUSH BX
	SHL BX,1
	MUL A[BX-2] ;MULָ�AX�Ĵ���������A����Ӧλ�õ�ֵ��ˣ������˻��洢��AX�Ĵ�����
	POP BX  ;BX-2��ԭ��BX-2 �����ڼ�������Ԫ�ص�ƫ���������� A ��һ��˫�ֽ����飬ÿ��Ԫ��ռ�������ֽڵĴ洢�ռ䣬������Ҫ�� BX �Ĵ�����ֵ��ȥ 2 �������ȷ��ƫ����
	ADD AX,CARRY ;����λ�洢��CARRY������
	MOV DX,0
	MOV CX,10
	DIV CX ;��AX�Ĵ����е�ֵ��10���г������㣬�̴洢��AX�Ĵ����У������洢��DX�Ĵ����С�ͨ��DIVָ����ɳ�������
	MOV CARRY,AX
	PUSH BX
	SHL BX,1 ;��Ϊ���� A �е�Ԫ����˫�ֽڣ�16λ����С�ģ��� BX �Ĵ�����ֵ����һλ���൱�ڽ������ 2
	MOV A[BX-2],DX
	POP BX
	POP AX ;DX�Ĵ����е�ֵ�洢������A����Ӧλ�ã��ָ�BX�Ĵ�����ֵ������AX�Ĵ����е�ֵ����
	CMP BX,ANUM
	JZ DCARRY ;�Ƚ�BX�Ĵ�����ֵ��ANUM������ֵ�������ȣ�����ת��DCARRY��ǩ
	INC BX
	JMP AMUL ;�������ȣ���BX�Ĵ�����ֵ����1������ת��AMUL��ǩ��������һ�ֵĽ׳˼���

DCARRY:
	PUSH AX
	CMP CARRY,0
	JZ DONE ;��AX�Ĵ�����ֵѹ��ջ�С�Ȼ��Ƚ�CARRY������ֵ�Ƿ�Ϊ0�����Ϊ0������ת��DONE��ǩ����ִ��ջ�ͼĴ��������������������
	INC BX
	MOV AX,CARRY
	MOV DX,0
	MOV CX,10
	DIV CX ;��BX�Ĵ�����ֵ����1������CARRY������ֵ�洢��AX�Ĵ����С����ţ�ʹ�ó���ָ�AX�Ĵ�����ֵ��10���г������㣬�̴洢��AX�Ĵ����У������洢��DX�Ĵ�����
	PUSH BX
	SHL BX,1
	MOV A[BX-2],DX
	POP BX
	MOV CARRY,AX
	MOV CX,ANUM
	INC CX
	MOV ANUM,CX ;��DX�Ĵ�����ֵ�洢������A����Ӧλ�ã��ָ�BX�Ĵ�����ֵ������AX�Ĵ����е�ֵ������������AX�Ĵ�����ֵ�洢��CARRY�����У�����ANUM������ֵ����1
	POP AX
	JMP DCARRY
DONE:
	POP AX
	POP AX
	POP BX
	POP CX
	POP DX
	RET
FACTORIAL ENDP

SHUCHU PROC
	MOV DX,OFFSET OUTSTR
	MOV AH,09H
	INT 21H
	MOV BX,ANUM ;���׳˽����Ԫ�ظ����洢�ڼĴ��� BX �У���������ѭ��������
BEGINO:
	PUSH BX
	SHL BX,1 ;�� BX �Ĵ�����ֵѹ��ջ�У�Ȼ��������һλ���൱�ڳ��� 2
	MOV AX,A[BX-2] ;ʹ�� MOV AX, A[BX-2] ָ��,������ A ��ȡ����Ӧ�Ľ׳˽��,A[BX-2] ��ͨ���� BX �Ĵ�����ֵ��ȥ 2 ����������Ԫ�ص�ƫ��������Ϊÿ��Ԫ��ռ�������ֽ�
	POP BX
	ADD AL,30H
	MOV DL,AL
	MOV AH,02H
	INT 21H ;���ַ�תʮ���ƴ�ӡ���
	CMP BX,1
	JZ ENDO
	DEC BX
	JMP BEGINO ;�Ƚ� BX �Ĵ�����ֵ�Ƿ���� 1������ǣ�����ת����ǩ ENDO������ѭ�������򣬽� BX �Ĵ�����ֵ��һ��Ȼ����ת��ѭ����ǩ BEGINO��������һ��ѭ��
ENDO: RET
SHUCHU ENDP

END_0:
	mov ax,4c00h
	int 21h

CODES ENDS
    END START



