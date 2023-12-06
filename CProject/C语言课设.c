#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<conio.h>
#include<math.h>
#include <time.h>
typedef struct information/*结构体*/
{
	char ID[100];/*订单编号*/
	char name[100];/*订餐人姓名*/
	char phonenumber[100];/*订餐人电话*/
	int peoplenumber;/*就餐人数*/ 
	int hour;/*就餐时*/
	int minute;/*就餐分*/
	int table;/*桌型*/
	int YN;/*订餐是否成功*/
	int table1;/*桌号*/
	struct information *next;
}InformationLink,*Pointer;

InformationLink stu[100];

int i,j,k;
int a=0;//表征C类桌子 
int b=0;//表征B类桌子 
int c=0;//表征A类桌子 
int d=0;//表征11到12订餐数 
int e=0;//表征12到13订餐数 
int f=0;//表征13到12订餐数 
int g=0;
int h=0;
int num;
int count1=0;
int tabledel=1;
int tablecount=0;

void input(Pointer *head);
void sort(Pointer head);
void statistic(Pointer head);
void search(Pointer head);
void search1(Pointer head);
void search2(Pointer head);
void search3(Pointer head);
void change(Pointer head);
void del(Pointer *head);
void close(Pointer head);
void sort1(Pointer head);
void sort2(Pointer head);
void sort3(Pointer head);
void distribute(Pointer *head);





int main()
{
	system("color 3E");
	time_t timep;
    char s[100];
    time(&timep);
    strcpy(s,ctime(&timep));
	int a;//记录目的号 
	int b;//读入密码 
	int c;
	printf("                 *****************************\n");
	printf("                 ***********欢迎您！**********\n");
	printf("                 *****************************\n");
	system("pause");
	system("cls");
	printf("                 *******************************************\n");
	printf("                 *                                         *\n");
	printf("                 *                                         *\n");
	printf("                 *             请输入登录密码：            *\n");
	printf("                 *                                         *\n");
	printf("                 *                                         *\n");
	printf("                 *******************************************\n");
	scanf("%d",&b);
	while(b!=211004)
	{
	printf("                 *********密码输入错误，请重新输入！********\n"); 
	scanf("%d",&b);
	
	}
	printf("                 *****************登录成功！****************\n");
	Pointer head=NULL;
	do{
	   system("cls");
	   printf("                    当前时间： %s", s);
	   printf("                 **************电话订餐系统***************\n");
	   printf("                 *              1  录入信息              *\n");
	   printf("                 *              2  排序功能              *\n");
   	   printf("                 *              3  统计功能              *\n");
	   printf("                 *              4  查询功能              *\n");
	   printf("                 *              5  修改功能              *\n");
	   printf("                 *              6  删除功能              *\n");
	   printf("                 *              7  退出系统              *\n");
	   printf("                 *****************************************\n");
	   printf("                             请输入对应的功能编号:          ");
	   scanf("%d",&a);
	
	   switch(a)
	   {
	   	case 1:input(&head);break;
	   	case 2:sort(head);break;
	   	case 3:statistic(head);break;
	   	case 4:search(head);break;
	   	case 5:change(head);break;
	   	case 6:del(&head);break;
	   	case 7:
	   		    {
				printf("                 您确定要退出系统吗？\n                 输入0确认退出，输入7取消退出");
	   	        scanf("%d",&a);
		        break;}
	   	default:printf("                 请重新输入:");break;
	   }
      }while(a!=0);
}

//***********功能区***************

void input(Pointer *head)
{
	system("cls");
	char in_ID[100]={0};
	Pointer p,q,r,z;
	printf("                 请输入编号：");
    scanf("%s",in_ID);
	p=q=*head;
	z=*head;
	while(p!=NULL)
	{
		if(strcmp(p->ID,in_ID)==0)
		{
            printf("该订餐编号已出现，请重新输入，新的订餐编号为：");
			scanf("%s",in_ID);
		}
         else
         {
         	q=p;
			p=p->next;
		 }	
	}
	r=(Pointer)malloc(sizeof(InformationLink));
	r->next=NULL;
	
	if(r==NULL)
	{
		printf("                 空间分配失败!");
	}
	if(q==NULL)
	*head=r;
	else
	q->next=r;
    memcpy(r->ID,in_ID,strlen(in_ID));
    
	printf("                 请输入姓名:  ");
	scanf("%s",r->name);
	printf("                 请输入电话号码:  ");
	scanf("%s",r->phonenumber);
	printf("                 请输入就餐人数:  ");
	scanf("%d",&r->peoplenumber);
	printf("                 请输入就餐__时:");
	scanf("%d",&r->hour);
	while(r->hour<11||r->hour>13)
	{
		printf("                 对不起，您输入的时段餐馆不营业！请重新输入：");
		scanf("%d",&r->hour);
	}
	printf("                 请输入__分:");
	scanf("%d",&r->minute);
	while(r->minute>59)
	{
		printf("                 输入的数据有误，请重新输入:");
		scanf("%d",&r->minute);
	}
	
	if(r->peoplenumber>10)
	r->YN=1;
	if(r->peoplenumber<=10)
	{
		r->YN=0;
		if(r->peoplenumber<=4)
		{
			r->table=1;
			a++;
		}
		if(r->peoplenumber>4&&r->peoplenumber<=7)
		{
			r->table=2;
			b++;
		}
		if(r->peoplenumber>7)
		{
			r->table=3;
			c++;
		}
		if(a>5||b>5||c>5)
		r->YN=1;
	}
	if(r->YN==1)
	{
		printf("                 订餐是否成功：否");
	    printf("                   餐桌类型：/\n"); 
	}
	if(r->YN==0&&a<=5&&b<=5&&c<=5)
	{
		printf("                 订餐是否成功：是");
		if(r->table==1)
		{
			printf("                   餐桌类型：C");
			if(z!=NULL&&a==5)/*避免由删除信息造成的漏洞*/
			{
				tablecount=0;
			    while(z!=NULL)
			    {
			    	if(z->table==1)
			    	{
					tablecount=tablecount+z->table1;
			        }
			        z=z->next;
				}
				tabledel=15-tablecount;
				r->table1=tabledel;
				printf("%d\n",tabledel);
			}
			else
			{
			r->table1=a;
			printf("%d\n",a); 
		    }
		}
		if(r->table==2)
		{
			printf("                   餐桌类型：B");
			if(z!=NULL&&b==5)/*避免由删除信息造成的漏洞*/
			{
				tablecount=0;
			    while(z!=NULL)
			    {
			    	if(z->table==2)
			    	{
					tablecount=tablecount+z->table1;
			        }
			        z=z->next;
				}
				tabledel=15-tablecount;
				r->table1=tabledel;
				printf("%d\n",tabledel);
			}
			else
			{
				r->table1=b;
				printf("%d\n",b); 
			}
			
		}
		if(r->table==3)
		{
			printf("                   餐桌类型：A");
			if(z!=NULL&&c==5)/*避免由删除信息造成的漏洞*/
			{
				tablecount=0;
			    while(z!=NULL)
			    {
			    	if(z->table==3)
			    	{
					tablecount=tablecount+z->table1;
			        }
			        z=z->next;
				}
				tabledel=15-tablecount;
				r->table1=tabledel;
				printf("%d\n",tabledel);
			}
			else
			{
				r->table1=c;
				printf("%d\n",c); 
			}
		}
		if(r->YN==0&&a>5&&b>5&&c>5)
		printf("                 抱歉，餐桌已订满!");
	}
	system("pause");
}


void sort(Pointer head)
{
	system("cls");
	int p;
	printf("                 *****************************************\n");
    printf("                 *           1 按订单编号排序            *\n");
    printf("                 *           2 按就餐时间排序            *\n");
    printf("                 *           3 按就餐人数排序            *\n");
    printf("                 *****************************************\n");
    scanf("%d",&p);
    if(p==1)
    sort1(head);
    if(p==2)
    sort2(head);
    if(p==3)
    sort3(head);
}

void statistic(Pointer head)
{
	system("cls");
	Pointer p;
	p=head;
	printf(" ________________________________________________________________________________________________\n");
	printf("|  订餐编号  |  订餐者姓名  |  订餐者电话  |  用餐时间  |  用餐人数  |  订餐是否成功  |  餐桌号  |\n ");
	d=0;
	e=0;
	f=0;
	while(p!=NULL)
	{
		printf("________________________________________________________________________________________________\n");
		 printf("|%s  |",p->ID);
		 printf("%s        |",p->name);
		 printf(" %s  |",p->phonenumber);
		 printf(" %d:",p->hour);
		 printf(" %d     | ",p->minute);
		 printf(" %d         | ",p->peoplenumber);
		 if(p->YN==0)
		 {printf("是             |");
		 if(p->table==1)
		 {printf("C");
		 printf(" %d      |",p->table1); } 
		 if(p->table==2)
		 {printf("B");
		 printf(" %d      |",p->table1); } 
		 if(p->table==3)
		 {printf("A");
		   printf(" %d       |",p->table1); } 
		 } 
		 if(p->YN==0)
		 {
		 	count1++;
		 	if(p->hour>=11&&p->hour<12)
	        d++;
	        if(p->hour>=12&&p->hour<13)
	        e++;
	        if(p->hour>=13&&p->hour<14)
	        f++;
		 }
		 else
		 { 
		    printf("否           |");
		    printf("            |",p->table1); 
		 } 
		 printf("\n");
		 p=p->next;
	}
	printf("_________________________________________________________________________________________________\n");
	printf("*************************\n");
	printf("A型餐桌的已订桌数为：%d\n",c);
	printf("B型餐桌的已订桌数为：%d\n",b);
	printf("C型餐桌的已订桌数为：%d\n",a);
	printf("11时段订餐成功次数为：%d\n",d);
	printf("12时段订餐成功次数为：%d\n",e);
	printf("13时段订餐成功次数为：%d\n",f);
	printf("订餐成功的订单数为：%d\n",count1);
	printf("*************************\n");
	count1=0;
    system("pause");
}


void search(Pointer head)
{
	system("cls");
	int p;
    printf("                 *****************************************\n");
    printf("                 *           1 按订单编号查询            *\n");
    printf("                 *           2 按就餐时间查询            *\n");
    printf("                 *           3 按订餐人姓名查询          *\n");
    printf("                 *****************************************\n");
    scanf("%d",&p);
    if(p==1)
    search1(head);
    if(p==2)
    search2(head);
    if(p==3)
    search3(head);
}

void search1(Pointer head)
{
	system("cls");
	char ID[30],flag=1;
	Pointer p=head;
	printf("                 请输入要查询的编号:");
	scanf("%s",ID);
	printf(" ________________________________________________________________________________________________\n");
	printf("|  订餐编号  |  订餐者姓名  |  订餐者电话  |  用餐时间  |  用餐人数  |  订餐是否成功  |  餐桌号  |\n ");
	while(p!=NULL&&flag)
	{
		if(strcmp(ID,p->ID)==0)
		{
			printf("________________________________________________________________________________________________\n");
			printf("|%s  |",p->ID);
		    printf("%s        |",p->name);
		    printf(" %s  |",p->phonenumber);
		    printf(" %d:",p->hour);
		    printf(" %d     | ",p->minute);
	    	printf(" %d         | ",p->peoplenumber);
		    if(p->YN==0)
		    {printf("是             |");
		    if(p->table==1)
		    {printf("C");
		    printf(" %d      |",p->table1); } 
		    if(p->table==2)
		    {printf("B");
		    printf(" %d      |",p->table1); } 
		    if(p->table==3)
		    {printf("A");
		    printf(" %d       |",p->table1); } 
		    }
			else
			{
			printf("否           |");
		    printf("            |",p->table1);
		    }
			flag=0;
		}
		else
			p=p->next;	
			
	}
	printf("_________________________________________________________________________________________________\n");
	if(flag)
		printf("                 没查询到!");
		system("pause");
}

void search2(Pointer head)
{
	system("cls");
	int o,x,flag=1;
	Pointer p=head;
	printf("                 请输入时:");
	scanf("%d",&o);
	printf("                 请输入分:");
	scanf("%d",&x);
	printf(" ________________________________________________________________________________________________\n");
	printf("|  订餐编号  |  订餐者姓名  |  订餐者电话  |  用餐时间  |  用餐人数  |  订餐是否成功  |  餐桌号  |\n ");
	while(p!=NULL)
	{
		if(o==p->hour&&x==p->minute)
		{
			printf("________________________________________________________________________________________________\n");
			printf("|%s  |",p->ID);
		    printf("%s        |",p->name);
		    printf(" %s  |",p->phonenumber);
		    printf(" %d:",p->hour);
		    printf(" %d     | ",p->minute);
	    	printf(" %d         | ",p->peoplenumber);
		    if(p->YN==0)
		    {printf("是             |");
		    if(p->table==1)
		    {printf("C");
		    printf(" %d      |",p->table1); } 
		    if(p->table==2)
		    {printf("B");
		    printf(" %d      |",p->table1); } 
		    if(p->table==3)
		    {printf("A");
		    printf(" %d       |",p->table1); } 
		    }
			else
			{
			printf("否           |");
		    printf("            |",p->table1);
		    }
			flag=0;
		}
		p=p->next;	
	}
	printf("_________________________________________________________________________________________________\n");
	if(flag)
		printf("                 没查询到!");
		system("pause");
		
}

void search3(Pointer head)
{
	system("cls");
	char name[100],flag=1;
	Pointer p=head;
	printf("                 请输入订餐者姓名:");
	scanf("%s",name);
	printf(" ________________________________________________________________________________________________\n");
	printf("|  订餐编号  |  订餐者姓名  |  订餐者电话  |  用餐时间  |  用餐人数  |  订餐是否成功  |  餐桌号  |\n ");
	while(p!=NULL)
	{
		if(strcmp(name,p->name)==0)
		{
		printf("________________________________________________________________________________________________\n");
			printf("|%s  |",p->ID);
		    printf("%s        |",p->name);
		    printf(" %s  |",p->phonenumber);
		    printf(" %d:",p->hour);
		    printf(" %d     | ",p->minute);
	    	printf(" %d         | ",p->peoplenumber);
		    if(p->YN==0)
		    {printf("是             |");
		    if(p->table==1)
		    {printf("C");
		    printf(" %d      |",p->table1); } 
		    if(p->table==2)
		    {printf("B");
		    printf(" %d      |",p->table1); } 
		    if(p->table==3)
		    {printf("A");
		    printf(" %d       |",p->table1); } 
		    }
			else
			{
			printf("否           |");
		    printf("            |",p->table1);
		    }
			flag=0;
		}
		p=p->next;	
	}
	printf("_________________________________________________________________________________________________\n");
	if(flag)
		printf("                 没查询到!");
		system("pause");
}

void change(Pointer head)
{
	system("cls");
	char ID[100],flag=1;
	int x;
	Pointer p=head;
	printf("                 请输入要修改数据的编号：");
	scanf("%s",ID);
	while(p!=NULL&&flag)
	{
		if(strcmp(p->ID,ID)==0)
		{
			printf("                 已查询到该订单，您确定要修改数据吗？\n");
			printf("                     1 是     2 否   \n") ;
			scanf("%d",&x);
			while(x!=1&&x!=2)
			{
				printf("您的输入错误，请再次输入：\n");
				scanf("%d",&x);
			}
			if(x==2)
			return;
			if(p->hour>=11&&p->hour<12)
	        d--;
	        if(p->hour>=12&&p->hour<13)
	        e--;
	        if(p->hour>=13&&p->hour<14)
	        f--;
		    printf("                 请输入姓名:  ");
	        scanf("%s",p->name);
	        printf("                 请输入电话号码:  ");
	        scanf("%s",p->phonenumber);
        	printf("                 请输入用餐总人数：");
	        scanf("%d",&p->peoplenumber);
	        printf("                 请输入用餐时：");
	        scanf("%d",&p->hour);
	        while(p->hour<11||p->hour>13)
	         {
		       printf("                 对不起，您输入的时段餐馆不营业！请重新输入：");
		       scanf("%d",&p->hour);
	         }
	        printf("                 请输入__分:");
	        scanf("%d",&p->minute);
	        while(p->minute>59)
	          {
		       printf("                 输入的数据有误，请重新输入:");
		       scanf("%d",&p->minute);
	          }
	        if(p->hour>=11&&p->hour<12)
	        d++;
	        if(p->hour>=12&&p->hour<13)
	        e++;
        	if(p->hour>=13&&p->hour<14)
         	f++;
        	if(p->peoplenumber>10)
	        p->YN=1;
        	if(p->peoplenumber<=10)
        	{
		        p->YN=0;
	        	if(p->peoplenumber<=4)
	        	{
		         	p->table=1;
		        	a++;
	        	}
	        	if(p->peoplenumber>4&&p->peoplenumber<=7)
	        	{
		        	p->table=2;
		        	b++;
	        	}
	        	if(p->peoplenumber>7)
	         	{
	        		p->table=3;
		        	c++;
	        	}
	        	if(a>5||b>5||c>5)
	        	p->YN=1;
	        }
         	if(p->YN==1)
         	{
	        	printf("                 修改是否成功：否");
	            printf("  餐桌类型：/\n"); 
         	}
         	if(p->YN==0&&a<=5&&b<=5&&c<=5)
         	{
         		printf("                 修改是否成功：是");
	        	if(p->table==1)
	         	{
		        	printf("  餐桌类型：C");
		        	printf("%d\n",a); 
		         	p->table1=a;
         		}
         		if(p->table==2)
	        	{
        			printf("  餐桌类型：B");
		        	printf("%d\n",b); 
	        		p->table1=b;
         		}
         		if(p->table==3)
        		{
	         		printf("  餐桌类型：A");
	        		printf("%d\n",c); 
	         		p->table1=c;
	        	}
         		if(p->YN==0&&a>5&&b>5&&c>5)
	        	printf("                 抱歉，餐桌已订满!");
        	}
			flag=0;	
		}
		else
		p=p->next;
	}
	if(flag)
	printf("                 没有查询到可以修改的数据!\n");
	system("pause");
}

void del(Pointer *head)
{
	system("cls");
	char ID[100],flag=1;
	Pointer p,q;
	int w;
	printf("                 请输入要删除数据的订单编号:");
	scanf("%s",ID);
	p=q=*head;
	while(p!=NULL&&flag)
	{
	    if(strcmp(p->ID,ID)==0)
	    {
	        printf("                 您确定要删除该订单吗？\n");
	        printf("                   1 是     2 否       \n");
	        scanf("%d",&w);
	        if(w==2)
	        {
		       flag=0;
	           break;
	        }
	        if(w==1)
	            {
	            if(p->peoplenumber<=4)
			    a--;
		        if(p->peoplenumber>4&&p->peoplenumber<=7)
		     	b--;
		        if(p->peoplenumber>7)
		    	c--;
		        if(p==*head)
	      	    {
		    	*head=p->next;
			    free(p);
	    	    }
	    	    else
	    	    {
	    		q->next=p->next;
	    		free(p);
		     	}
		    	flag=0;
	         	}
	         	system("pause");
	    }
	    else
	    {
		q=p;
		p=p->next;
	    }
	}
	if(flag)
	{
	printf("                 无此订单号\n");
	system("pause");
    }
}

void sort1(Pointer head)	
{
	system("cls");
	Pointer p;
	p=head;
	int count=0;
	InformationLink temp;
	for(i=0;p!=NULL;i++)
	{
		strcpy(stu[i].ID,p->ID);
		stu[i].hour=p->hour;
		stu[i].minute=p->minute;
		strcpy(stu[i].name,p->name);
		stu[i].peoplenumber =p->peoplenumber ;
		strcpy(stu[i].phonenumber,p->phonenumber) ;
		stu[i].YN =p->YN;
		stu[i].table=p->table;
		stu[i].table1=p->table1;
	    p=p->next;
	    count++;
	}
	for(i=0;i<count;i++)
	{
		for(k=0;k<count;k++)
		{
			
			if(strcmp(stu[k].ID,stu[k+1].ID)>0)
			{
				temp=stu[k];
				stu[k]=stu[k+1];
				stu[k+1]=temp;
			}
		}
	}
	     printf(" ________________________________________________________________________________________________\n");
	     printf("|  订餐编号  |  订餐者姓名  |  订餐者电话  |  用餐时间  |  用餐人数  |  订餐是否成功  |  餐桌号  |\n ");
	for(i=1;i<count+1;i++)
	{    
	     printf("________________________________________________________________________________________________\n");
		 printf("|%s  |",stu[i].ID);
		 printf("%s        |",stu[i].name);
		 printf(" %s  |",stu[i].phonenumber);
		 printf(" %d:",stu[i].hour);
		 printf(" %d     | ",stu[i].minute);
		 printf(" %d         | ",stu[i].peoplenumber);
		 if(stu[i].YN==0)
		 {printf("是             |");
		 if(stu[i].table==1)
		 {printf("C");
		 printf(" %d      |",stu[i].table1); } 
		 if(stu[i].table==2)
		 {printf("B");
		 printf(" %d      |",stu[i].table1); } 
		 if(stu[i].table==3)
		 {printf("A");
		   printf(" %d       |",stu[i].table1); } 
		 } 
		 else
		 { 
		   printf("否           |");
		   printf("            |",stu[i].table1); 
		 } 
		 printf("\n"); 
	}
    printf("_________________________________________________________________________________________________\n");
    for(i=1;i<count+1;i++)
    {
    	strcpy(stu[i].ID,"0");
		stu[i].hour=0;
		stu[i].minute=0;
		strcpy(stu[i].name,"0");
		stu[i].peoplenumber =0 ;
		strcpy(stu[i].phonenumber,"0") ;
		stu[i].YN =0;
		stu[i].table=0;
		stu[i].table1=0;
	}
	system("pause");
}

void sort2(Pointer head)
{
	system("cls");
	Pointer p;
	p=head;
	int count=0;
	InformationLink temp;
	for(i=0;p!=NULL;i++)
	{
		strcpy(stu[i].ID,p->ID);
		stu[i].hour=p->hour;
		stu[i].minute=p->minute;
		strcpy(stu[i].name,p->name);
		stu[i].peoplenumber =p->peoplenumber ;
		strcpy(stu[i].phonenumber,p->phonenumber) ;
		stu[i].YN =p->YN;
		stu[i].table=p->table;
		stu[i].table1=p->table1;
	    p=p->next;
	    count++;
	    
	}
	for(i=0;i<count;i++)
	{
		for(k=0;k<count;k++)
		{
			if(stu[k].hour>stu[k+1].hour)
			{
				temp=stu[k];
				stu[k]=stu[k+1];
				stu[k+1]=temp;
			}
			if(stu[k].hour==stu[k+1].hour)
			{
				if(stu[k].minute>=stu[k+1].minute)
				{
					temp=stu[k];
				stu[k]=stu[k+1];
				stu[k+1]=temp;
				}
			}
		}
	}
printf(" ________________________________________________________________________________________________\n");
	     printf("|  订餐编号  |  订餐者姓名  |  订餐者电话  |  用餐时间  |  用餐人数  |  订餐是否成功  |  餐桌号  |\n ");
	for(i=1;i<count+1;i++)
	{    
	     printf("________________________________________________________________________________________________\n");
		 printf("|%s  |",stu[i].ID);
		 printf("%s        |",stu[i].name);
		 printf(" %s  |",stu[i].phonenumber);
		 printf(" %d:",stu[i].hour);
		 printf(" %d     | ",stu[i].minute);
		 printf(" %d         | ",stu[i].peoplenumber);
		 if(stu[i].YN==0)
		 {printf("是             |");
		 if(stu[i].table==1)
		 {printf("C");
		 printf(" %d      |",stu[i].table1); } 
		 if(stu[i].table==2)
		 {printf("B");
		 printf(" %d      |",stu[i].table1); } 
		 if(stu[i].table==3)
		 {printf("A");
		   printf(" %d       |",stu[i].table1); } 
		 } 
		 else
		 { 
		   printf("否           |");
		   printf("            |",stu[i].table1); 
		 } 
		 printf("\n");
		 
	}
    printf("_________________________________________________________________________________________________\n");
    for(i=1;i<count+1;i++)
    {
    	strcpy(stu[i].ID,"0");
		stu[i].hour=0;
		stu[i].minute=0;
		strcpy(stu[i].name,"0");
		stu[i].peoplenumber =0 ;
		strcpy(stu[i].phonenumber,"0") ;
		stu[i].YN =0;
		stu[i].table=0;
		stu[i].table1=0;
	}
	system("pause");
}

void sort3(Pointer head)
{
	system("cls");
	Pointer p;
	p=head;
	int count=0;
	InformationLink temp;
	for(i=0;p!=NULL;i++)
	{
		strcpy(stu[i].ID,p->ID);
		stu[i].hour=p->hour;
		stu[i].minute=p->minute;
		strcpy(stu[i].name,p->name);
		stu[i].peoplenumber =p->peoplenumber ;
		strcpy(stu[i].phonenumber,p->phonenumber) ;
		stu[i].YN =p->YN;
		stu[i].table=p->table;
		stu[i].table1=p->table1;
	    p=p->next;
	    count++;
	}
	for(i=0;i<count;i++)
	{
		for(k=0;k<count;k++)
		{
			if(stu[k].peoplenumber>stu[k+1].peoplenumber)
			{
				temp=stu[k];
				stu[k]=stu[k+1];
				stu[k+1]=temp;
			}
		}
	}
	printf(" ________________________________________________________________________________________________\n");
	     printf("|  订餐编号  |  订餐者姓名  |  订餐者电话  |  用餐时间  |  用餐人数  |  订餐是否成功  |  餐桌号  |\n ");
	for(i=1;i<count+1;i++)
	{    
	     printf("________________________________________________________________________________________________\n");
		 printf("|%s  |",stu[i].ID);
		 printf("%s        |",stu[i].name);
		 printf(" %s  |",stu[i].phonenumber);
		 printf(" %d:",stu[i].hour);
		 printf(" %d     | ",stu[i].minute);
		 printf(" %d         | ",stu[i].peoplenumber);
		 if(stu[i].YN==0)
		 {printf("是             |");
		 if(stu[i].table==1)
		 {printf("C");
		 printf(" %d      |",stu[i].table1); } 
		 if(stu[i].table==2)
		 {printf("B");
		 printf(" %d      |",stu[i].table1); } 
		 if(stu[i].table==3)
		 {printf("A");
		   printf(" %d       |",stu[i].table1); } 
		 } 
		 else
		 { 
		   printf("否           |");
		   printf("            |",stu[i].table1); 
		 } 
		 printf("\n");
		 
	}
    printf("_________________________________________________________________________________________________\n");
    for(i=1;i<count+1;i++)
    {
    	strcpy(stu[i].ID,"0");
		stu[i].hour=0;
		stu[i].minute=0;
		strcpy(stu[i].name,"0");
		stu[i].peoplenumber =0 ;
		strcpy(stu[i].phonenumber,"0") ;
		stu[i].YN =0;
		stu[i].table=0;
		stu[i].table1=0;
	}
	system("pause");
}
