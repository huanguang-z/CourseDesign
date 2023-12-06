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

MOV 	AH, 	9             	;输出登录成功的提示
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
   	
   	MOV AH,01H      ;键盘输入并显示，字符存于AL中
   	INT 21H
   	   	
   	CMP AL,'Y'      ;比较刚输入的字符与Y字符，相等跳转到BEGIN_1中
   	JE BEGIN_1
   	
   	CMP AL,'N'      ;比较刚输入的字符与N字符，相等跳转到END_0中，不等则跳转到ASK重输入
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
	MOV CX,5 ;读取字符数目
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
	AND AX,000FH ;清除高四位
	XCHG AX,BP
	MUL BX 
	ADD BP,AX ;乘法指令将输入的数字乘以 10，然后将乘积与 BP 寄存器相加，将结果存储在 BP
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
	MOV AX,BP ;BP寄存器的值存入AX寄存器，并与1进行比较
	CMP BP,1
	JNZ BEGIN ;如果BP不等于1，则跳转到BEGIN标签处执行递归操作
	MOV A[0],AX
	POP AX
	POP BX
	POP CX
	POP DX
	RET
BEGIN:
	DEC BP
	CALL FACTORIAL ;将BP寄存器的值减1，并调用自身的递归操作
	MOV BX,1
AMUL:
	PUSH AX
	PUSH BX
	SHL BX,1
	MUL A[BX-2] ;MUL指令将AX寄存器与数组A中相应位置的值相乘，并将乘积存储在AX寄存器中
	POP BX  ;BX-2的原因：BX-2 是用于计算数组元素的偏移量。由于 A 是一个双字节数组，每个元素占用两个字节的存储空间，所以需要将 BX 寄存器的值减去 2 来获得正确的偏移量
	ADD AX,CARRY ;将进位存储在CARRY变量中
	MOV DX,0
	MOV CX,10
	DIV CX ;将AX寄存器中的值与10进行除法运算，商存储在AX寄存器中，余数存储在DX寄存器中。通过DIV指令完成除法运算
	MOV CARRY,AX
	PUSH BX
	SHL BX,1 ;因为数组 A 中的元素是双字节（16位）大小的，将 BX 寄存器的值左移一位，相当于将其乘以 2
	MOV A[BX-2],DX
	POP BX
	POP AX ;DX寄存器中的值存储在数组A的相应位置，恢复BX寄存器的值，并将AX寄存器中的值弹出
	CMP BX,ANUM
	JZ DCARRY ;比较BX寄存器的值与ANUM变量的值，如果相等，则跳转到DCARRY标签
	INC BX
	JMP AMUL ;如果不相等，则将BX寄存器的值增加1，并跳转到AMUL标签处进行下一轮的阶乘计算

DCARRY:
	PUSH AX
	CMP CARRY,0
	JZ DONE ;将AX寄存器的值压入栈中。然后比较CARRY变量的值是否为0，如果为0，则跳转到DONE标签处，执行栈和寄存器的清理操作，并返回
	INC BX
	MOV AX,CARRY
	MOV DX,0
	MOV CX,10
	DIV CX ;将BX寄存器的值增加1，并将CARRY变量的值存储在AX寄存器中。接着，使用除法指令将AX寄存器的值与10进行除法运算，商存储在AX寄存器中，余数存储在DX寄存器中
	PUSH BX
	SHL BX,1
	MOV A[BX-2],DX
	POP BX
	MOV CARRY,AX
	MOV CX,ANUM
	INC CX
	MOV ANUM,CX ;将DX寄存器的值存储在数组A的相应位置，恢复BX寄存器的值，并将AX寄存器中的值弹出。继续将AX寄存器的值存储在CARRY变量中，并将ANUM变量的值增加1
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
	MOV BX,ANUM ;将阶乘结果的元素个数存储在寄存器 BX 中，它被用作循环计数器
BEGINO:
	PUSH BX
	SHL BX,1 ;将 BX 寄存器的值压入栈中，然后将其左移一位（相当于乘以 2
	MOV AX,A[BX-2] ;使用 MOV AX, A[BX-2] 指令,从数组 A 中取出对应的阶乘结果,A[BX-2] 是通过将 BX 寄存器的值减去 2 来计算数组元素的偏移量，因为每个元素占用两个字节
	POP BX
	ADD AL,30H
	MOV DL,AL
	MOV AH,02H
	INT 21H ;将字符转十进制打印输出
	CMP BX,1
	JZ ENDO
	DEC BX
	JMP BEGINO ;比较 BX 寄存器的值是否等于 1，如果是，则跳转到标签 ENDO，结束循环。否则，将 BX 寄存器的值减一，然后跳转回循环标签 BEGINO，继续下一次循环
ENDO: RET
SHUCHU ENDP

END_0:
	mov ax,4c00h
	int 21h

CODES ENDS
    END START



