#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<conio.h>
#include<math.h>
#include <time.h>
typedef struct information/*�ṹ��*/
{
	char ID[100];/*�������*/
	char name[100];/*����������*/
	char phonenumber[100];/*�����˵绰*/
	int peoplenumber;/*�Ͳ�����*/ 
	int hour;/*�Ͳ�ʱ*/
	int minute;/*�Ͳͷ�*/
	int table;/*����*/
	int YN;/*�����Ƿ�ɹ�*/
	int table1;/*����*/
	struct information *next;
}InformationLink,*Pointer;

InformationLink stu[100];

int i,j,k;
int a=0;//����C������ 
int b=0;//����B������ 
int c=0;//����A������ 
int d=0;//����11��12������ 
int e=0;//����12��13������ 
int f=0;//����13��12������ 
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
	int a;//��¼Ŀ�ĺ� 
	int b;//�������� 
	int c;
	printf("                 *****************************\n");
	printf("                 ***********��ӭ����**********\n");
	printf("                 *****************************\n");
	system("pause");
	system("cls");
	printf("                 *******************************************\n");
	printf("                 *                                         *\n");
	printf("                 *                                         *\n");
	printf("                 *             �������¼���룺            *\n");
	printf("                 *                                         *\n");
	printf("                 *                                         *\n");
	printf("                 *******************************************\n");
	scanf("%d",&b);
	while(b!=211004)
	{
	printf("                 *********��������������������룡********\n"); 
	scanf("%d",&b);
	
	}
	printf("                 *****************��¼�ɹ���****************\n");
	Pointer head=NULL;
	do{
	   system("cls");
	   printf("                    ��ǰʱ�䣺 %s", s);
	   printf("                 **************�绰����ϵͳ***************\n");
	   printf("                 *              1  ¼����Ϣ              *\n");
	   printf("                 *              2  ������              *\n");
   	   printf("                 *              3  ͳ�ƹ���              *\n");
	   printf("                 *              4  ��ѯ����              *\n");
	   printf("                 *              5  �޸Ĺ���              *\n");
	   printf("                 *              6  ɾ������              *\n");
	   printf("                 *              7  �˳�ϵͳ              *\n");
	   printf("                 *****************************************\n");
	   printf("                             �������Ӧ�Ĺ��ܱ��:          ");
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
				printf("                 ��ȷ��Ҫ�˳�ϵͳ��\n                 ����0ȷ���˳�������7ȡ���˳�");
	   	        scanf("%d",&a);
		        break;}
	   	default:printf("                 ����������:");break;
	   }
      }while(a!=0);
}

//***********������***************

void input(Pointer *head)
{
	system("cls");
	char in_ID[100]={0};
	Pointer p,q,r,z;
	printf("                 �������ţ�");
    scanf("%s",in_ID);
	p=q=*head;
	z=*head;
	while(p!=NULL)
	{
		if(strcmp(p->ID,in_ID)==0)
		{
            printf("�ö��ͱ���ѳ��֣����������룬�µĶ��ͱ��Ϊ��");
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
		printf("                 �ռ����ʧ��!");
	}
	if(q==NULL)
	*head=r;
	else
	q->next=r;
    memcpy(r->ID,in_ID,strlen(in_ID));
    
	printf("                 ����������:  ");
	scanf("%s",r->name);
	printf("                 ������绰����:  ");
	scanf("%s",r->phonenumber);
	printf("                 ������Ͳ�����:  ");
	scanf("%d",&r->peoplenumber);
	printf("                 ������Ͳ�__ʱ:");
	scanf("%d",&r->hour);
	while(r->hour<11||r->hour>13)
	{
		printf("                 �Բ����������ʱ�β͹ݲ�Ӫҵ�����������룺");
		scanf("%d",&r->hour);
	}
	printf("                 ������__��:");
	scanf("%d",&r->minute);
	while(r->minute>59)
	{
		printf("                 �����������������������:");
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
		printf("                 �����Ƿ�ɹ�����");
	    printf("                   �������ͣ�/\n"); 
	}
	if(r->YN==0&&a<=5&&b<=5&&c<=5)
	{
		printf("                 �����Ƿ�ɹ�����");
		if(r->table==1)
		{
			printf("                   �������ͣ�C");
			if(z!=NULL&&a==5)/*������ɾ����Ϣ��ɵ�©��*/
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
			printf("                   �������ͣ�B");
			if(z!=NULL&&b==5)/*������ɾ����Ϣ��ɵ�©��*/
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
			printf("                   �������ͣ�A");
			if(z!=NULL&&c==5)/*������ɾ����Ϣ��ɵ�©��*/
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
		printf("                 ��Ǹ�������Ѷ���!");
	}
	system("pause");
}


void sort(Pointer head)
{
	system("cls");
	int p;
	printf("                 *****************************************\n");
    printf("                 *           1 �������������            *\n");
    printf("                 *           2 ���Ͳ�ʱ������            *\n");
    printf("                 *           3 ���Ͳ���������            *\n");
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
	printf("|  ���ͱ��  |  ����������  |  �����ߵ绰  |  �ò�ʱ��  |  �ò�����  |  �����Ƿ�ɹ�  |  ������  |\n ");
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
		 {printf("��             |");
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
		    printf("��           |");
		    printf("            |",p->table1); 
		 } 
		 printf("\n");
		 p=p->next;
	}
	printf("_________________________________________________________________________________________________\n");
	printf("*************************\n");
	printf("A�Ͳ������Ѷ�����Ϊ��%d\n",c);
	printf("B�Ͳ������Ѷ�����Ϊ��%d\n",b);
	printf("C�Ͳ������Ѷ�����Ϊ��%d\n",a);
	printf("11ʱ�ζ��ͳɹ�����Ϊ��%d\n",d);
	printf("12ʱ�ζ��ͳɹ�����Ϊ��%d\n",e);
	printf("13ʱ�ζ��ͳɹ�����Ϊ��%d\n",f);
	printf("���ͳɹ��Ķ�����Ϊ��%d\n",count1);
	printf("*************************\n");
	count1=0;
    system("pause");
}


void search(Pointer head)
{
	system("cls");
	int p;
    printf("                 *****************************************\n");
    printf("                 *           1 ��������Ų�ѯ            *\n");
    printf("                 *           2 ���Ͳ�ʱ���ѯ            *\n");
    printf("                 *           3 ��������������ѯ          *\n");
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
	printf("                 ������Ҫ��ѯ�ı��:");
	scanf("%s",ID);
	printf(" ________________________________________________________________________________________________\n");
	printf("|  ���ͱ��  |  ����������  |  �����ߵ绰  |  �ò�ʱ��  |  �ò�����  |  �����Ƿ�ɹ�  |  ������  |\n ");
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
		    {printf("��             |");
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
			printf("��           |");
		    printf("            |",p->table1);
		    }
			flag=0;
		}
		else
			p=p->next;	
			
	}
	printf("_________________________________________________________________________________________________\n");
	if(flag)
		printf("                 û��ѯ��!");
		system("pause");
}

void search2(Pointer head)
{
	system("cls");
	int o,x,flag=1;
	Pointer p=head;
	printf("                 ������ʱ:");
	scanf("%d",&o);
	printf("                 �������:");
	scanf("%d",&x);
	printf(" ________________________________________________________________________________________________\n");
	printf("|  ���ͱ��  |  ����������  |  �����ߵ绰  |  �ò�ʱ��  |  �ò�����  |  �����Ƿ�ɹ�  |  ������  |\n ");
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
		    {printf("��             |");
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
			printf("��           |");
		    printf("            |",p->table1);
		    }
			flag=0;
		}
		p=p->next;	
	}
	printf("_________________________________________________________________________________________________\n");
	if(flag)
		printf("                 û��ѯ��!");
		system("pause");
		
}

void search3(Pointer head)
{
	system("cls");
	char name[100],flag=1;
	Pointer p=head;
	printf("                 �����붩��������:");
	scanf("%s",name);
	printf(" ________________________________________________________________________________________________\n");
	printf("|  ���ͱ��  |  ����������  |  �����ߵ绰  |  �ò�ʱ��  |  �ò�����  |  �����Ƿ�ɹ�  |  ������  |\n ");
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
		    {printf("��             |");
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
			printf("��           |");
		    printf("            |",p->table1);
		    }
			flag=0;
		}
		p=p->next;	
	}
	printf("_________________________________________________________________________________________________\n");
	if(flag)
		printf("                 û��ѯ��!");
		system("pause");
}

void change(Pointer head)
{
	system("cls");
	char ID[100],flag=1;
	int x;
	Pointer p=head;
	printf("                 ������Ҫ�޸����ݵı�ţ�");
	scanf("%s",ID);
	while(p!=NULL&&flag)
	{
		if(strcmp(p->ID,ID)==0)
		{
			printf("                 �Ѳ�ѯ���ö�������ȷ��Ҫ�޸�������\n");
			printf("                     1 ��     2 ��   \n") ;
			scanf("%d",&x);
			while(x!=1&&x!=2)
			{
				printf("��������������ٴ����룺\n");
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
		    printf("                 ����������:  ");
	        scanf("%s",p->name);
	        printf("                 ������绰����:  ");
	        scanf("%s",p->phonenumber);
        	printf("                 �������ò���������");
	        scanf("%d",&p->peoplenumber);
	        printf("                 �������ò�ʱ��");
	        scanf("%d",&p->hour);
	        while(p->hour<11||p->hour>13)
	         {
		       printf("                 �Բ����������ʱ�β͹ݲ�Ӫҵ�����������룺");
		       scanf("%d",&p->hour);
	         }
	        printf("                 ������__��:");
	        scanf("%d",&p->minute);
	        while(p->minute>59)
	          {
		       printf("                 �����������������������:");
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
	        	printf("                 �޸��Ƿ�ɹ�����");
	            printf("  �������ͣ�/\n"); 
         	}
         	if(p->YN==0&&a<=5&&b<=5&&c<=5)
         	{
         		printf("                 �޸��Ƿ�ɹ�����");
	        	if(p->table==1)
	         	{
		        	printf("  �������ͣ�C");
		        	printf("%d\n",a); 
		         	p->table1=a;
         		}
         		if(p->table==2)
	        	{
        			printf("  �������ͣ�B");
		        	printf("%d\n",b); 
	        		p->table1=b;
         		}
         		if(p->table==3)
        		{
	         		printf("  �������ͣ�A");
	        		printf("%d\n",c); 
	         		p->table1=c;
	        	}
         		if(p->YN==0&&a>5&&b>5&&c>5)
	        	printf("                 ��Ǹ�������Ѷ���!");
        	}
			flag=0;	
		}
		else
		p=p->next;
	}
	if(flag)
	printf("                 û�в�ѯ�������޸ĵ�����!\n");
	system("pause");
}

void del(Pointer *head)
{
	system("cls");
	char ID[100],flag=1;
	Pointer p,q;
	int w;
	printf("                 ������Ҫɾ�����ݵĶ������:");
	scanf("%s",ID);
	p=q=*head;
	while(p!=NULL&&flag)
	{
	    if(strcmp(p->ID,ID)==0)
	    {
	        printf("                 ��ȷ��Ҫɾ���ö�����\n");
	        printf("                   1 ��     2 ��       \n");
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
	printf("                 �޴˶�����\n");
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
	     printf("|  ���ͱ��  |  ����������  |  �����ߵ绰  |  �ò�ʱ��  |  �ò�����  |  �����Ƿ�ɹ�  |  ������  |\n ");
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
		 {printf("��             |");
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
		   printf("��           |");
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
	     printf("|  ���ͱ��  |  ����������  |  �����ߵ绰  |  �ò�ʱ��  |  �ò�����  |  �����Ƿ�ɹ�  |  ������  |\n ");
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
		 {printf("��             |");
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
		   printf("��           |");
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
	     printf("|  ���ͱ��  |  ����������  |  �����ߵ绰  |  �ò�ʱ��  |  �ò�����  |  �����Ƿ�ɹ�  |  ������  |\n ");
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
		 {printf("��             |");
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
		   printf("��           |");
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
