DATAS SEGMENT
   FNAME DB 'TEMP.txt',0
   ERROR1 DB 'File not found' , 07H, 0
   ERROR2 DB 'Reading error' , 07H, 0
	STRING1 DB  'Number of $' ;各类字符提示信息
	STRING2 DB ': $'
	STRING3 DB 'Number of Others $'
	ARRAY DB 26 DUP(0);字母个数数组
	OTHERS DB 0
	BUFFER DB ?;数据缓冲区
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

	MESG1 		DB 		'INPUT YOUR USERNAME: $'		;提示字符
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
	_ULEN  		EQU 	$-_USERNAME         ; 用户名长度
	_PLEN  		EQU 	$-_PASSWORD       	; 密码长度
	
	USERNAME  	DB		15   				; 输入的用户名
	PASSWORD  	DB 		15 DUP(?)          	; 输入的密码
	PLEN  		DB 		0                  	; 输入密码的长度
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
      MOV AL,20H;空格的ASCII码是20H
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
;------------发声-------------
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
;--------------发声调用函数----------------
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
		MOV 	AH, 	9    			;09H功能号，显示DS：DL内字符串
      	MOV 	DX, 	OFFSET MESG1
      	INT 	21H
      	MOV 	AH, 	0AH   			;键盘输入字符串并显示，存在U缓冲区
		MOV 	DX, 	OFFSET USERNAME  		;用户输入字符串起始偏移位置放入DX缓冲区
      	INT 	21H
      	MOV 	AH, 	2
      	MOV 	DL, 	0AH            	;换行字符
      	INT 	21H
      	MOV 	AH, 	9             	;显示提示字符串
      	MOV 	DX, 	OFFSET MESG2
      	INT 	21H
      	MOV 	CX, 	_PLEN
      	MOV 	SI, 	OFFSET PASSWORD  	;把输入密码起始偏移地址放入SI
NEXT1:  MOV 	AH, 	07H         	;无回显从键盘读入一个字符
      	INT 	21H
      	CMP 	AL, 	0DH          	;判断输入是否结束
      	JE 	IND
      	MOV 	[SI], 	AL       		;将输入密码字符放入数据区：PWD
      	MOV 	AH, 2          			;每输入一位密码显示一个‘*’
      	MOV 	DL, '*'
      	INT 	21H
      	INC 	PLEN
      	INC 	SI
      	JMP 	NEXT1
IND:  	MOV 	AH, 	2             	;输出换行
      	MOV 	DL, 	0AH
      	INT 	21H
     	MOV 	BX, 	OFFSET USERNAME+1    	;[BX]=实际输入字符数
      	MOV 	AL, 	[BX]
      	CMP 	AL, 	_ULEN          	;比较用户名长度    
      	MOV   BX, 	OFFSET _USERNAME
      	MOV 	SI, 	OFFSET _USERNAME+2
      	MOV 	CX, 	_ULEN          	;设置循环次数
NEXT2:  MOV 	AL, 	[BX]
      	CMP 	[SI], 	AL          	;循环比较用户名是否正确
      	INC   	SI
      	INC 	BX
      	LOOP 	NEXT2
      	MOV 	BX, 	OFFSET _PASSWORD
      	MOV 	SI, 	OFFSET PASSWORD
      	MOV 	CL, 	PLEN
      	MOV 	CH, 	0
      	CMP 	CX, 	_PLEN          	;比较密码长度
      	JNZ 	ERR
      	MOV 	CX, 	_PLEN
NEXT3:  MOV 	AL, 	[BX]
      	CMP 	[SI], 	AL          	;循环比较密码字符是否正确
      	JNZ 	ERR
      	INC 	SI
      	INC 	BX
      	LOOP 	NEXT3
      	JMP 	WEL
ERR:  	MOV 	AH, 	9
      	MOV 	DX, 	OFFSET ERROR 	;输出用户名或密码错误提示
      	INT 	21H
      	MOV 	AH, 	2
      	MOV 	DL, 	0AH
		INT 	21H
      	JMP 	NEXT0
WEL:  	MOV 	AH, 	9             	;输出登录成功的提示
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
   	
   	MOV AH,01H      ;键盘输入并显示，字符存于AL中
   	INT 21H
   	   	
   	CMP AL,'Y'      ;比较刚输入的字符与Y字符，相等跳转到BEGIN_1中
   	JE BEGIN_1
   	
   	CMP AL,'N'      ;比较刚输入的字符与N字符，相等跳转到END_0中，不等则跳转到ASK重输入
   	JE END_0
	
	
WRONG:	LEA DX,ANS
   	MOV AH,09H
   	INT 21H
   	JMP INPUT
   	
BEGIN_1:
	mov dl,0DH ;打印换行
    mov ah,2H
    int 21H
    MOV DL,0AH
    MOV AH,2h
    INT 21H
    
    LEA DI,ARRAY
    MOV cx,26
Initial: ;初始化清零
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
    MOV AX,3D00H ;读打开指定文件
    INT 21H
    MOV ah,0
    JNC OPEN ; OPEN SUCCESSFULLY
    MOV SI, OFFSET ERROR1
    CALL DMESS
    JMP OVER

; JUMP SUCCESSFULLY
OPEN:
	MOV BX,AX; 保存文件句柄
GO: CALL READ_CHAR ;从文件读一个字符
	JC READ_ERROR
	CMP AL,EOF ; IS OVER?
	JZ TYPE_OK
	CALL PUNCH; 记录每个字符个数
	JMP GO; 循环读取
	
READ_ERROR:
	MOV SI,OFFSET ERROR2
	CALL DMESS

TYPE_OK:
	MOV AH,3EH
	INT 21H ; 关闭文件
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
	MOV AL,OTHERS ;将统计的数字送回给AX
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
	MOV CX,1 ;设置单次读取的字符数为1
	MOV DX,OFFSET BUFFER
	MOV AH,3FH; 读取文件
	INT 21H
	JC R1 ;读出错
	CMP AX,CX ;判断文件是否读取完毕
	MOV AL,EOF
	JB R2
	MOV AL,BUFFER
R2:CLC
R1:RET ;字节出栈
READ_CHAR ENDP
	
DMESS PROC
DMESS1:
	MOV DL,[SI]
	INC SI
	OR DL,DL
	JZ DMESS2
	MOV AH,02H
	INT 21H ;显示一个字符
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
	JB OTHER ;当字符小于41H（A），其余字符数目加一
	CMP CH,5AH
	JA HIGHER2; 当字符大于5AH(Z),继续比较
H1:CMP CH,CL
	JE CHAR ; 对应字母个数++
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
	JB OTHER ;当字符小于61H，其余字符数目加一
	CMP CH,7AH
	JA OTHER ;当字符大于7AH，其余字符加一
H2: CMP CH,CL
	JE CHAR ; 对应字母个数加一
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
; 比较结束
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
	MOV AL,[SI] ;将统计的数字送回给AX
	ADD SI,1
	CALL DISPLAY
	CALL ENDLINE
	INC DI
	CMP DI,5BH ;判断DI是否是字母
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
	DIV BL ;以十进制数方式输出
	PUSH AX
	; 输出十位数
	MOV DL,AL
	ADD DL,30H
	MOV AH,02H
	INT 21H
	POP AX
	;输出个位数
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

