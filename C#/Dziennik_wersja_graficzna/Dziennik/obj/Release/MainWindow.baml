   M S B A M L   `   `   `  ���� C  ?Dziennik, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null#clr-namespace:DziennikDziennik      Dziennik.MainWindow   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     @d2http://schemas.microsoft.com/expression/blend/2008     Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     (localclr-namespace:Dziennik       � Title$  
MainWindow��$	��450��$	��525��.��� .+�o� -    o�Name$
 menu��$��19��$
��auto��$	��Top=����	#FFA0AAEA.@�m� $
��File��.@�m� -   $��_Exit��m� $��Other��.@�m� -   $��About application���� m� -   $��About author���� $��33��$
��auto��$��Bottom=�����  �� EndPoint$ 0.5,1&� �� 
StartPoint$ 0.5,0&� �� Opacity$	 0.5Q�.���  � Color$ White��� $ 	#FFF1F109�� � Offset$ 0.438Q�� $ 	#FFFEFEF4��.@��� -    ��Name$ labelStatusBar��$��
Status bar���� -    ��Name$ 
tabControl��$
��auto��$��	0,24,0,33q�$
��auto��.@��� $��Users��$
��Left=�$��20��$	��Top=�$��54������ $	 0,1&�$	 0,0&�.��� $ 	#FFF0F0F0��$ 0Q�� $ 	#FF78C8C8��$ 1Q�.��� ���� $ 0.5,1&�$ 0.5,0&�$
 0.75Q�.��� $ 	#FF78C8C8��� $ White��$ 1Q�.+��� -   $ labelSelectUser��$��Select user:��$
��Left=�$��	10,10,0,0q�$	��Top=��� -   $ 	labelName��$��Name:��$
��Left=�$��
314,46,0,0q�$	��Top=��� -	   $ labelSurname��$��Surname:��$
��Left=�$	��Top=�$��
299,72,0,0q��� -
   $ labelFolder��$��Folder:��$
��Left=�$��312,103,0,0q�$	��Top=�	 J� RenderTransformOrigin$	 0.763,0.346&��� -   
 ��Name$
 textBoxName��$
��Left=�$��23��$��
363,46,0,0q� �� TextWrapping$
 Wrap=�$�� ��$	��Top=�$	��120���� -   $
 textBoxSurname��$
��Left=�$��23��$��
363,76,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -   $
 textBoxFolder��$
��Left=�$��23��$��363,107,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -    ��Name$ 
buttonSave��$��Save changes��$
��Left=�$��408,189,0,0q�$	��Top=�$��75����
� .��� $ 	#FF74FF20��$ 1Q�� $ White���� -    ��Name$ listBoxUsers��$
��Left=�$��85��$��	10,45,0,0q�$	��Top=�$	��209���� -   $ 
buttonLoad��$
��Load��$
��Left=�$��
24,189,0,0q�$	��Top=�$��75����
� .��� $ 	#FF74F096��$ 1Q�� $ White���� -   $ label4��$��
User data:��$
��Left=�$��
363,10,0,0q�$	��Top=��� -   $ buttonDelete��$��Delete user��$
��Left=�$��
24,225,0,0q�$	��Top=�$��75����
� .��� $ 	#FFFA9696��$ 1Q�� $ White���� -   $ 	buttonAdd��$��Add user��$
��Left=�$��408,225,0,0q�$	��Top=�$��75����
� .��� $ 	#FFFAFA45��$ 1Q�� $ White���� -   $ labelUserNumber��$��User number:��$
��Left=�$��277,134,0,0q�$	��Top=��� -   $
 textBoxUserNumber��$
��Left=�$��23��$��363,138,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120��{�.  �� $
��Date��$
��Left=�$��20��$	��Top=�$��54������ $	 0,1&�$	 0,0&�.��� $ 	#FFF0F0F0��$ 0Q�� $ 	#FF64C8C8��$ 1Q�.��� ���� $ 0.5,1&� � MappingMode$ RelativeToBoundingBox=�$ 0.5,0&�$
 0.75Q�.��� $ White��$ 1Q�� $ 	#FF64C8C8��.+�&   System.Windows.Controls.Calendar  -     Name$ CalendarItem��$
��Left=�$��	10,64,0,0q�$	��Top=��� -   $ labelDSelectDate��$��Select date:��$
��Left=�$��	10,33,0,0q�$	��Top=��� -   $ buttonDApply��$��Apply date and load data��$
��Left=�$��
10,237,0,0q�$	��Top=�$	��179����
� .��� $ 	#FFF7FF45��$ 1Q�� $ 	#FFBEEAA0���� -   $ labelSelectedDate��$
��Date��$
��Left=�$��
103,33,0,0q�$	��Top=��� -   $ labelLoadedDate��$��Loaded date:��$
��Left=�$��	10,10,0,0q�$	��Top=��� -   $ labelCurrentlyLoadedDate��$
��Date��$
��Left=�$��
103,10,0,0q�$	��Top=��� $	��BMI��$
��Left=�$��20��$	��Top=�$��54������ $	 0,1&�$	 0,0&�.��� $ 	#FFF0F0F0��$ 0Q�� $ 	#FF50C8C8��$ 1Q�.��� ���� $ 0.5,1&�$ 0.5,0&�.��� $ 	#FF50C8C8��� $ White��$ 1Q�.+��� -   $ labelBmiData��$��BMI data��$
��Left=�$��	29,10,0,0q�$	��Top=��� -   $ labelWeight��$��Weight:��$
��Left=�$��	36,58,0,0q�$	��Top=��� -   $ labelHeight��$��Height:��$
��Left=�$��	38,89,0,0q�$	��Top=��� -   $ labelBmiIndex��$��
BMI index:��$
��Left=�$��
21,117,0,0q�$	��Top=��� -    $ buttonBMISave��$��	Save data��$
��Left=�$��164,167,0,0q�$	��Top=�$��75����
� .��� $ 	#FF74F020��$ 1Q�� $ White���� -!   $
 textBoxBMIWeight��$
��Left=�$��23��$��
119,58,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -"   $
 textBoxBMIHeight��$
��Left=�$��23��$��
119,89,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -#   $
 textBoxBMIIndex��$
��Left=�$��23��$��119,120,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120��{�.  �� -$   $ buttonLoadBMI��$��	Calculate��$
��Left=�$��
36,167,0,0q�$	��Top=�$��75����
� .��� $ 	#FFF7FF45��$ 1Q�� $ White���� $��Liquids��$
��Left=�$��20��$	��Top=�$��54������ $	 0,1&�$	 0,0&�.��� $ 	#FFF0F0F0��$ 0Q�� $ 	#FF3CC8C8��$ 1Q�.��� ���� $ 0.5,1&�$ 0.5,0&�.��� $ 	#FF3CC8C8��� $ White��$ 1Q�.+��� -%   $ labelLiquids��$��Amount of liquids��$
��Left=�$��	10,10,0,0q�$	��Top=��� -&   $ labelAmount��$��Amount:��$
��Left=�$��	60,61,0,0q�$	��Top=��� -'   $
 textBoxLiquids��$
��Left=�$��23��$��
120,64,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -(   $ buttonLiquids��$��Save amount��$
��Left=�$��165,104,0,0q�$	��Top=�$��75����
� .��� $ 	#FF74F020��$ 1Q�� $ White���� $
��Food��$
��Left=�$��20��$	��Top=�$��54������ $	 0,1&�$	 0,0&�.��� $ 	#FFF0F0F0��$ 0Q�� $ 	#FF28C8C8��$ 1Q�.��� ���� $ 0.5,1&�$ 0.5,0&�.��� $ 	#FF28C8C8��� $ White��$ 1Q�.+��� -)   $ labelMealsOfTheDay��$��Meals of the day��$
��Left=�$��	16,10,0,0q�$	��Top=��� -*   $ labelBreakfast��$��
Breakfast:��$
��Left=�$��	53,62,0,0q�$	��Top=��� -+   $ labelSecondBreakfast��$��Second breakfast:��$
��Left=�$��	10,93,0,0q�$	��Top=��� -,   $ labelDinner��$��Dinner:��$
��Left=�$��
66,124,0,0q�$	��Top=��� --   $ labelAfternoonMeal��$��Afternoon meal:��$
��Left=�$��
19,155,0,0q�$	��Top=��� -.   $ labelSupper��$��Supper:��$
��Left=�$��
64,186,0,0q�$	��Top=��� -/   $
 textBoxBreakfast��$
��Left=�$��23��$��
119,65,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -0   $
 textBoxSecondBreakfast��$
��Left=�$��23��$��
119,96,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -1   $
 textBoxDinner��$
��Left=�$��23��$��119,127,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -2   $
 textBoxAfternoonMeal��$
��Left=�$��23��$��119,158,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -3   $
 textBoxSupper��$
��Left=�$��23��$��119,189,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -4   $ buttonSaveMeal��$��Saved meals��$
��Left=�$��164,234,0,0q�$	��Top=�$��75����
� .��� $ 	#FF74F020��$ 1Q�� $ White���� $��Exercise��$
��Left=�$��20��$	��Top=�$��54������ $	 0,1&�$	 0,0&�.��� $ 	#FF14C8C8��$ 1Q�� $ 	#FFE5E5E5��.��� ���� $ 0.5,1&�$ 0.5,0&�.��� $ 	#FF14C8C8��$ 0Q�� $ White��$ 1Q�.+��� -5   $ labelExercises��$��	Exercises��$
��Left=�$��	96,10,0,0q�$	��Top=��� -6   $ labelHowManyMinutes��$��How many minutes?:��$
��Left=�$��	33,82,0,0q�$	��Top=��� -7   $ labelTypeOfExercise��$��Type of exercise:��$
��Left=�$��
56,113,0,0q�$	��Top=��� -8   $
 textBoxHowManyMinutes��$
��Left=�$��23��$��
163,86,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -9   $
 textBoxTypeOfExercis��$
��Left=�$��23��$��163,117,0,0q�$
 Wrap=�$�� ��$	��Top=�$	��120���� -:   $ buttonExercises��$��Save exercise��$
��Left=�$��208,161,0,0q�$	��Top=�$��75����
� .��� $ 	#FF74F020��$ 1Q�� $ White���� -;    ��Name$ checkBoxExerciseToday��$�� ��$
��Left=�$��
163,64,0,0q�$	��Top=��� -<   $ labelExerciseToday��$��Did you exercise today?:��$
��Left=�$��	15,56,0,0q�$	��Top=�