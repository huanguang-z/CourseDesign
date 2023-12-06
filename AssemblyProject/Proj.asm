DATAS SEGMENT
   FNAME DB 'TEMP.txt',0
   ERROR1 DB 'File not found' , 07H, 0
   ERROR2 DB 'Reading error' , 07H, 0
	STRING1 DB  'Number of $' ;�����ַ���ʾ��Ϣ
	STRING2 DB ': $'
	STRING3 DB 'Number of Others $'
	ARRAY DB 26 DUP(0);��ĸ��������
	OTHERS DB 0
	BUFFER DB ?;���ݻ�����
	EOF DB 032H
	ASK_1 DB 13,10,'Do you want to calculate the number of letters?','$'
	ASK_2 DB 13,10,'Please input Y/N:','$'
	ASK_3 DB 13,10,'Do you want to continue to calculate the number of letters?','$'
	ANS DB 13,10,'The character is wrong, please input again','$'

	 infor1 db  "                welocom you to come here listeng!", '$'
	
	 mus_freg dw 196,262
		  dw 262,294,330
		  dw 392,330,330
		  dw 330,196
		  dw 262
		  dw 294,262,220
		  dw 196,196
		  dw 262,262,294,330
		  dw 440,330,392
		  dw 330,294
		  dw 262
		  dw 294,392
		  dw 330,294
		  dw 330,392
		  dw 392,330,392
		  dw 440,524,440
		  dw 330,294,262
		  dw 294,330,330
		  dw 196,392
		  dw 330,330
		  dw 294,262,262
		  dw 196,294,262
		  dw 330,294,262
		  dw 262
		  dw -1
	 mus_time  dw 25,75
		  dw 50,12,12
		  dw 25,25,25
		  dw 25,50
		  dw 75
		  dw 25,25,25
		  dw 125,25
		  dw 75,50,12,12
		  dw 25,25,50
		  dw 25,25
		  dw 75
		  dw 50,25
		  dw 25,100
		  dw 25,75
		  dw 25,25,25
		  dw 25,25,75
		  dw 25,50,25
		  dw 50,25,125
		  dw 25,100
		  dw 25,25
		  dw 25,25,75
		  dw 25,50,25
		  dw 50,12,12
		  dw 200

	MESG1 		DB 		'INPUT YOUR USERNAME: $'		;��ʾ�ַ�
	MESG2  		DB 		'INPUT YOUR PASSWORD: $' 
	ERROR  		DB 	'-----USERNAME OR PASSWORD ERROR!----$'
	WELCOME 	DB  0DH, 0AH, '   +-----------------------------------+', 0DH, 0AH
				DB '   |                    	       |', 0DH, 0AH
				DB '   |                     	       |', 0DH, 0AH			   
				DB '   |    Welcome to project 1 of ZWR    |', 0DH, 0AH
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

	_USERNAME  	DB 		'zwr'
	_PASSWORD  	DB 		'csu'
	_ULEN  		EQU 	$-_USERNAME         ; �û�������
	_PLEN  		EQU 	$-_PASSWORD       	; ���볤��
	
	USERNAME  	DB		15   				; ������û���
	PASSWORD  	DB 		15 DUP(?)          	; ���������
	PLEN  		DB 		0                  	; ��������ĳ���
DATAS ENDS


SHOWBM MACRO b
     LEA DX,b
     MOV AH,9
     INT 21H
ENDM
 
ADDRESS MACRO A,B
     LEA SI,A
     LEA BP,DS:B
ENDM

empty MACRO X,N
      lea bx,X
      MOV AL,20H;�ո��ASCII����20H
      MOV CX,N
  del:mov [bx],al
      inc bx
      loop del
      ENDM

stack segment stack
      db 900 dup(?)
stack ends

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
	MOV DL,0DH
    MOV AH,2H
    INT 21h
    MOV DL,0AH
    MOV AH,2H
    INT 21h
    
 	MOV AX,0
	MOV BX,0
	MOV CX,0
	MOV DX,0
    MOV AX,DATAS
    MOV DS,AX
    MOV AX,STACK
    MOV SS,AX
    MOV SP,16
NEXT0:  
		MOV AX,0
		MOV BX,0
		MOV CX,0
		MOV DX,0
	MOV AX,DATAS
    MOV DS,AX
    MOV AX,STACK
    MOV SS,AX
    MOV SP,16
		MOV 	AH, 	9    			;09H���ܺţ���ʾDS��DL���ַ���
      	MOV 	DX, 	OFFSET MESG1
      	INT 	21H
      	MOV 	AH, 	0AH   			;���������ַ�������ʾ������U������
		MOV 	DX, 	OFFSET USERNAME  		;�û������ַ�����ʼƫ��λ�÷���DX������
      	INT 	21H
      	MOV 	AH, 	2
      	MOV 	DL, 	0AH            	;�����ַ�
      	INT 	21H
      	MOV 	AH, 	9             	;��ʾ��ʾ�ַ���
      	MOV 	DX, 	OFFSET MESG2
      	INT 	21H
      	MOV 	CX, 	_PLEN
      	MOV 	SI, 	OFFSET PASSWORD  	;������������ʼƫ�Ƶ�ַ����SI
NEXT1:  MOV 	AH, 	07H         	;�޻��ԴӼ��̶���һ���ַ�
      	INT 	21H
      	CMP 	AL, 	0DH          	;�ж������Ƿ����
      	JE 	IND
      	MOV 	[SI], 	AL       		;�����������ַ�������������PWD
      	MOV 	AH, 2          			;ÿ����һλ������ʾһ����*��
      	MOV 	DL, '*'
      	INT 	21H
      	INC 	PLEN
      	INC 	SI
      	JMP 	NEXT1
IND:  	MOV 	AH, 	2             	;�������
      	MOV 	DL, 	0AH
      	INT 	21H
     	MOV 	BX, 	OFFSET USERNAME+1    	;[BX]=ʵ�������ַ���
      	MOV 	AL, 	[BX]
      	CMP 	AL, 	_ULEN          	;�Ƚ��û�������    
      	MOV   BX, 	OFFSET _USERNAME
      	MOV 	SI, 	OFFSET _USERNAME+2
      	MOV 	CX, 	_ULEN          	;����ѭ������
NEXT2:  MOV 	AL, 	[BX]
      	CMP 	[SI], 	AL          	;ѭ���Ƚ��û����Ƿ���ȷ
      	INC   	SI
      	INC 	BX
      	LOOP 	NEXT2
      	MOV 	BX, 	OFFSET _PASSWORD
      	MOV 	SI, 	OFFSET PASSWORD
      	MOV 	CL, 	PLEN
      	MOV 	CH, 	0
      	CMP 	CX, 	_PLEN          	;�Ƚ����볤��
      	JNZ 	ERR
      	MOV 	CX, 	_PLEN
NEXT3:  MOV 	AL, 	[BX]
      	CMP 	[SI], 	AL          	;ѭ���Ƚ������ַ��Ƿ���ȷ
      	JNZ 	ERR
      	INC 	SI
      	INC 	BX
      	LOOP 	NEXT3
      	JMP 	WEL
ERR:  	MOV 	AH, 	9
      	MOV 	DX, 	OFFSET ERROR 	;����û��������������ʾ
      	INT 	21H
      	MOV 	AH, 	2
      	MOV 	DL, 	0AH
		INT 	21H
      	JMP 	NEXT0
WEL:  	MOV 	AH, 	9             	;�����¼�ɹ�����ʾ
      	MOV 	DX, 	OFFSET WELCOME
      	INT 	21H

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
    
INPUT:    LEA DX,ASK_2
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
   	JMP INPUT
   	
BEGIN_1:
	mov dl,0DH ;��ӡ����
    mov ah,2H
    int 21H
    MOV DL,0AH
    MOV AH,2h
    INT 21H
    
    LEA DI,ARRAY
    MOV cx,26
Initial: ;��ʼ������
	MOV AX,0
    MOV [DI],AX
    INC DI
    LOOP Initial
    
    
    
	MOV AX,0
	MOV BX,0
	MOV CX,0
	MOV DX,0
	MOV OTHERS,0
    MOV AX,DATAS
    MOV DS,AX
    MOV AX,3D00H ;����ָ���ļ�
    INT 21H
    MOV ah,0
    JNC OPEN ; OPEN SUCCESSFULLY
    MOV SI, OFFSET ERROR1
    CALL DMESS
    JMP OVER

; JUMP SUCCESSFULLY
OPEN:
	MOV BX,AX; �����ļ����
GO: CALL READ_CHAR ;���ļ���һ���ַ�
	JC READ_ERROR
	CMP AL,EOF ; IS OVER?
	JZ TYPE_OK
	CALL PUNCH; ��¼ÿ���ַ�����
	JMP GO; ѭ����ȡ
	
READ_ERROR:
	MOV SI,OFFSET ERROR2
	CALL DMESS

TYPE_OK:
	MOV AH,3EH
	INT 21H ; �ر��ļ�
	MOV DL,0AH
	MOV AH,02H
	INT 21H
	CALL SHOW

OVER:

	MOV AH,07H
	INT 21H
	

	LEA DX,STRING3
	MOV AH,09H
	INT 21H
	XOR AX,AX
	MOV AL,OTHERS ;��ͳ�Ƶ������ͻظ�AX
	CALL DISPLAY
	CALL ENDLINE
	
	MOV AH,07H
	INT 21H
	
	MOV AX,3
	INT 10H
	
	LEA DX,ASK_3
   	MOV AH,09H
   	INT 21H
   	
   	JMP INPUT
	
	
READ_CHAR PROC
	MOV CX,1 ;���õ��ζ�ȡ���ַ���Ϊ1
	MOV DX,OFFSET BUFFER
	MOV AH,3FH; ��ȡ�ļ�
	INT 21H
	JC R1 ;������
	CMP AX,CX ;�ж��ļ��Ƿ��ȡ���
	MOV AL,EOF
	JB R2
	MOV AL,BUFFER
R2:CLC
R1:RET ;�ֽڳ�ջ
READ_CHAR ENDP
	
DMESS PROC
DMESS1:
	MOV DL,[SI]
	INC SI
	OR DL,DL
	JZ DMESS2
	MOV AH,02H
	INT 21H ;��ʾһ���ַ�
	JMP DMESS1
DMESS2:RET
DMESS ENDP

PUNCH PROC
	PUSH DX
	MOV DL,AL
	MOV AH,02H
	INT 21H
	POP DX
	MOV CL,41H
	LEA DI,ARRAY
	MOV CH,AL
	CMP CH,CL
	JB OTHER ;���ַ�С��41H��A���������ַ���Ŀ��һ
	CMP CH,5AH
	JA HIGHER2; ���ַ�����5AH(Z),�����Ƚ�
H1:CMP CH,CL
	JE CHAR ; ��Ӧ��ĸ����++
	JA LOOP1
LOOP1:
	INC CL
	ADD DI,1
	JMP H1
	
HIGHER2:
	MOV CL,61H ;
	LEA DI,ARRAY
	MOV CH,AL
	CMP CH,CL
	JB OTHER ;���ַ�С��61H�������ַ���Ŀ��һ
	CMP CH,7AH
	JA OTHER ;���ַ�����7AH�������ַ���һ
H2: CMP CH,CL
	JE CHAR ; ��Ӧ��ĸ������һ
	JA LOOP2
	
LOOP2:
	INC CL
	ADD DI,1
	JMP H2
	
CHAR:
	XOR CH,CH
	MOV CH,[DI]
	
	INC CH
	MOV [DI],CH
	JMP TG
OTHER:
	INC OTHERS
; �ȽϽ���
TG:	RET
	PUNCH ENDP
	
SHOW PROC
	LEA SI,ARRAY
	MOV DI,41H
LOOP3:
	LEA DX,STRING1
	MOV AH,09H
	INT 21H
	MOV DX,DI
	MOV AH,02H
	INT 21H
	LEA DX,STRING2
	MOV AH,09H
	INT 21H
	XOR AX,AX
	MOV AL,[SI] ;��ͳ�Ƶ������ͻظ�AX
	ADD SI,1
	CALL DISPLAY
	CALL ENDLINE
	INC DI
	CMP DI,5BH ;�ж�DI�Ƿ�����ĸ
	JB LOOP3

	RET
	SHOW ENDP
	
ENDLINE PROC NEAR
	MOV DL,20H
	MOV AH,02H
	INT 21H

	MOV DL,20H
	MOV AH,02H
	INT 21H
	MOV DL,20H
	MOV AH,02H
	
	INT 21H
	MOV DL,20H
	MOV AH,02H
	INT 21H
	RET
	ENDLINE ENDP
	
DISPLAY PROC NEAR
	MOV BL,10
	DIV BL ;��ʮ��������ʽ���
	PUSH AX
	; ���ʮλ��
	MOV DL,AL
	ADD DL,30H
	MOV AH,02H
	INT 21H
	POP AX
	;�����λ��
	MOV DL,AH
	ADD DL,30H
	MOV AH,02H
	INT 21H
	MOV DL,20H
	MOV AH,02H
	INT 21H
	RET
	DISPLAY ENDP
	

END_0:
	mov ax,4c00h
	int 21h
	
CODES ENDS
    END START

