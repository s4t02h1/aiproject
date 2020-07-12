========================================================
=
=   Javaで作って学ぶ暗号技術
=
=   プログラムの解説
=
========================================================

Javaは、WindowsやLinux,MacOSなど多くのOSに対応していますが、本説明書は、WindowsXPを想定しています。その他のOSを使用している方は、必要に応じて読み替えてください。

1. このパッケージの構成

　このパッケージの構成は、以下のようになっています。
　[名前]という表記は、ディレクトリ名を表しています。

[ルートディレクトリ]
README.txt                      ：いま開いているファイルです

[gcd] ＜最大公約数計算＞
    GCDTrialDivision.java       ：リスト2.1
    GCDTrialDivisionMain.java   ：リスト2.2

    GCDEuclid.java              ：リスト2.3
    GCDEuclidMain.java          ：（コマンドライン･インターフェース）

    GCDBinaryEuclid.java        ：リスト2.4
    GCDBinaryEuclidMain.java    ：（コマンドライン･インターフェース）

    RunGCD.java                 ：リスト2.5

    GCDBigInteger.java          ：リスト2.6
    GCDBigIntegerMain.java      ：（コマンドライン･インターフェース）
    
[extendedeuclid] ＜最大公約数計算：拡張ユークリッド互除法＞
    ExtendedEuclidGCD.java      ：リスト2.7
    ExtendedEuclidGCDMain.java  ：リスト2.8

    ExtededEuclidGCDTest.java   ：JUnitによるテストで使用(GCDBigInteger.classも使用)
    
[modinverse] ＜指数計算（逆数計算）＞
    ModInverse.java             ：リスト2.9
    ModInverseMain.java         ：リスト2.10
    
[modpow] ＜べき乗剰余計算＞
    ModPowBinary.java           ：リスト2.11
    ModPowBinaryMain.java       ：リスト2.12

    ModPow2wary.java            ：リスト2.13
    ModPow2waryMain.java        ：リスト2.14

    ModPowSlidingWindow.java    ：リスト2.15(前半), 2.16(後半)
    ModPowSlidingWindowMain.java：リスト2.17

    RunModPow.java              ：リスト2.18
    
[crt] ＜中国人剰余定理＞
    SignWithCRT.java            ：リスト2.19
    SignWithCRTMain.java        ：リスト2.20
    
[probableprime] ＜素数判定＞
    FermatTest.java             ：リスト3.1
    FermatTestMain.java         ：リスト3.2

    MillerRabinTest.java        ：練習問題用
    MillerRabinTestMain.java    ：練習問題用
    
[aes] ＜AES暗号＞
    AESAlgorithm.java           ：リスト4.1～4.8
    AESOperation.java           ：リスト4.9～リスト4.11
    
[hash] ＜ハッシュ関数＞
    SHA1.java                   ：リスト5.1～5.3
    SHA256.java                 ：リスト5.4～5.6
    HMAC.java                   ：リスト5.7
    
[ssl] ＜SSL (Secure Socket Layer)＞
    [SSLtest]
        SSLClient.java          ：リスト6.1
        SSLServer.java          ：リスト6.2
    [miniSSL]
        miniSSLc.java           ：リスト6.3～リスト6.17
        miniSSLs.java           ：リスト6.18～リスト6.22


2. 必要なツールの準備

　収録したプログラムをコンパイルおよび実行するためには、Javaのコンパイル環境（Development Kit）と実行環境（バーチャルマシン）が必要です。また、一部のプログラムには、JUnitというプログラムテスト用プログラムを用いた、テストコードが含まれています。これらのプログラムは、JUnitによるテストを使用しない場合でも、JUnitをインストールしないと、コンパイルすることができません（JUnitをインストールしない場合は、JUnit関係の記述を削除してからコンパイルする必要があります）。

　・Java(TM) 2 SE Development Kit (JDK 5.0)   ：Javaプログラムの作成と実行に使用します。
　・JUnit4                                    ：Javaプログラムのテストに使用します。

　JDK 5.0は、執筆時点では、"http://java.sun.com/javase/downloads/index_jdk5.jsp"からダウンロードすることができます。DOWNLOADボタンをクリックすると、英語のページにジャンプします。license agreementのAcceptにチェックマークを入れた後に、windows版であれば"jdk-1_5_0_13-windows-i586-p.exe"をダウンロードしてください。現時点の最新版は、Update 13です。

　JDKをインストールすると、コマンドプロンプトから、"java"や"javac"といったコマンドを使用することができるようになります。javaプログラムは、"javac [ファイル名(拡張子有り)]"としてコンパイルし、"java [ファイル名(拡張子無し)]"として実行するのが基本です。

例）
test.java
というプログラムを作成したとしましょう。
>javac test.java
を実行すると、(コンパイルが成功したら)test.classというファイルが生成されます。さらに、
>java test
を実行すると、test.classが読み込まれて、作成したプログラムが実行されます。
（コマンドプロンプト上でコマンドを入力する場合、">"という記号を使います。）

Javaのバージョンは、コマンドラインで
>java -version
と入力すると、表示されます。

例）
>java -version
java version "1.5.0_11"
Java(TM) 2 Runtime Environment, Standard Edition (build 1.5.0_11-b03)
Java HotSpot(TM) Client VM (build 1.5.0_11-b03, mixed mode, sharing)

　JUnit4は、執筆時点では、"http://www.junit.org/"からダウンロードすることができます。Download JUnitをクリックして、ジャンプ先のページから、"junit-4.4.zip（執筆時点の最新版）"をダウンロードしてください。現時点の最新版は、version 4.4です。

　ダウンロードしたファイルを、適当なフォルダに解凍します。たとえば、"C:\Program Files\Java\junit4.4"に解凍します。その場合、環境変数CLASSPATHには"C:\Program Files\Java\junit4.4\junit-4.4.jar;C:\Program Files\Java\junit4.4"を追加しておきます。インストールが成功したかどうかは、以下の例のようにして確認することができます。

例）
>java org.junit.runner.JUnitCore org.junit.tests.AllTests
JUnit version 4.4
...............................................................................................................................................................................................................................................................................I.II.........................................................
Time: 43.503        （←所要時間が表示されます）

OK (329 tests)      （←結果が表示されます）


3. プログラムの使い方

　ここでは、収録されている各プログラムの内容と使い方を説明します。

3-1. RSA関係プログラムの使い方

【プログラム名】
 -GCDTrialDivision.java
 -GCDTrialDivisionMain.java
　
　GCDTrialDivision.javaには、試行割り算法を用いた最大公約数計算関数が含まれています。一方、GCDTrialDivisionMain.javaには、GCDTrialDivision.java内の関数を呼び出して計算結果を表示するプログラムが含まれています。動作確認を行いましょう。

[コンパイル]
"gcd"ディレクトリに移動して、
>javac GCDTrialDivision.java GCDTrialDivisionMain.java
を実行すれば、二つのプログラムをコンパイルすることができます。コンパイルがうまくいけば、"GCDTrialDivision.class", "GCDTrialDivisionMain.class"という二つのファイルが生成されます。GCDTrialDivisionMain.javaは、GCDTrialDivision.javaの中身を参照していますから、二つを同時にコンパイルしてください。

[実行]
"gcd"ディレクトリの中で、
>java -cp .. gcd.GCDTrialDivisionMain 780 660
を実行すれば、780と660の最大公約数である60が結果として表示されます。ここで、"-cp"というのは、クラスパスを設定するオプションです。ここでは、クラスパスとして親ディレクトリ".."を指定しています。GCDTrialDivisionMainは、GCDTrialDivisionMain.javaの先頭に記述されているように、"gcd"という名前のパッケージの一部として定義されています。よって、gcdという名前のパッケージ内のGCDTrialDivisionMain（という名前のクラスのmain関数）を実行しますという意味で、上記のように記述するのです。780と660は、この関数を実行する際の引数として渡されます。
　ここでは、"gcd"ディレクトリの中で実行したために、クラスパスの設定を必要としましたが、親ディレクトリで実行するのであれば、"-cp .."という指定は必要ありません。


【プログラム名】
 -GCDEuclid.java
 -GCDEuclidMain.java

GCDEuclid.javaには、ユークリッド互除法を用いた最大公約数計算関数が含まれています。GCDEuclidMain.javaには、GCDEuclid.java内の関数を呼び出して計算結果を表示するプログラムが含まれています。

[コンパイル]
"gcd"ディレクトリに移動して、
>javac GCDEuclid.java GCDEuclidMain.java
を実行することで、"GCDEuclid.class"と"GCDEuclidMain.class"が生成されます。

[実行]
"gcd"ディレクトリの中で、
>java -cp .. gcd.GCDEuclidMain 780 660
のように実行します。


【プログラム名】
 -GCDBinaryEuclid.java
 -GCDBinaryEuclidMain.java

　GCDBinaryEuclid.javaには、バイナリーユークリッド互除法を用いた最大公約数計算関数が含まれています。GCDBinaryEuclidMain.javaには、GCDBinaryEuclid.java内の関数を呼び出して計算結果を表示するプログラムが含まれています。

[コンパイル]
"gcd"ディレクトリに移動して、
>javac GCDBinaryEuclid.java GCDBinaryEuclidMain.java
を実行することで、"GCDBinaryEuclid.class"と"GCDBinaryEuclidMain.class"が生成されます。

[実行]
"gcd"ディレクトリの中で、
>java -cp .. gcd.GCDBinaryEuclidMain 780 660
のように実行します。


【プログラム名】
 -RunGCD.java
 -GCDBinaryEuclid.java

　RunGCD.javaは、バイナリーユークリッド互除法を用いた最大公約数の計算速度を評価するためのプログラムです。バイナリーユークリッド互除法を用いた最大公約数計算関数は、GCDBinaryEuclid.javaに含まれていますから、二つをあわせて使用します。

[コンパイル]
"gcd"ディレクトリに移動して、
>javac GCDBinaryEuclid.java RunGCD.java
を実行することで、"GCDBinaryEuclid.class"と"RunGCD.class"が生成されます。

[実行]
"gcd"ディレクトリの中で、
>java -cp .. gcd.RunGCD 1024 15000
のように実行します。このプログラムは、引数の設定によっては、結果が表示されるまでに膨大な時間がかかりますので注意してください。（上記の設定であれば、比較的新しいコンピュータでも、ひとつの結果を表示するのに数十秒かかります。）


【プログラム名】
 -GCDBigInteger.java
 -GCDBigIntegerMain.java

　GCDBigInteger.javaには、JavaのBigIntegerクラスが標準で実装している最大公約数の計算関数を使用して、最大公約数を計算するプログラムが含まれています。GCDBigIntegerMain.javaには、GCDBigInteger.java内の関数を呼び出して計算結果を表示するプログラムが含まれています。

[コンパイル]
"gcd"ディレクトリに移動して、
>javac GCDBigInteger.java GCDBigIntegerMain.java
を実行することで、"GCDBigInteger.class"と"GCDBigIntegerMain.class"が生成されます。

[実行]
"gcd"ディレクトリの中で、
>java -cp .. gcd.GCDBigIntegerMain 780 660
のように実行します。


【プログラム名】
 -ExtendedEuclidGCD.java
 -ExtendedEuclidGCDMain.java

　ExtendedEuclidGCD.javaには、拡張ユークリッド互除法を用いた最大公約数計算関数が含まれています。ExtendedEuclidGCDMain.javaには、ExtendedEuclidGCD.java内の関数を呼び出して計算結果を表示するプログラムが含まれています。

[コンパイル]
"extendedeuclid"ディレクトリに移動して、
>javac ExtendedEuclidGCD.java ExtendedEuclidGCDMain.java
を実行することで、"ExtendedEuclidGCD.class"と"ExtendedEuclidGCDMain.class"が生成されます。

[実行]
"extendedeuclid"ディレクトリの中で、
>java -cp .. extendedeuclid.ExtendedEuclidGCDMain 780 660
のように実行します。パッケージが先ほどの"gcd"から"extendedeuclid"に変わりましたので、実行時の指定方法が変わっていることに注意してください。実行の結果、最大公約数と一緒に、ax+by=gcd(a,b)を満たすx,yが出力されます（a,bは入力値です。上記の場合、a=780,b=660となります）。


【プログラム名】
 -ModInverse.java
 -ModInverseMain.java

　ModInverse.javaには、指数計算（逆数計算）関数が含まれています。ModInverseMain.javaには、ModInverse.java内の関数を呼び出して指数計算（逆数計算）結果を表示するプログラムが含まれています。

[コンパイル]
パッケージのルートディレクトリに移動して、
>javac modinverse\ModInverse.java modinverse\ModInverseMain.java
を実行することで、"modinverse"ディレクトリの下に、"ModInverse.class"と"ModInverseMain.class"が生成されます。先ほどと異なり、パッケージのルートディレクトリで実行しているところに注意してください。そろそろコンパイル方法にも慣れたと思うので、異なる呼び出し方をしています。実は、これまでのプログラムも同様に、パッケージのルートディレクトリからコンパイルと実行を行うことができます。

[実行]
パッケージのルートディレクトリで、
>java modinverse.ModInverseMain 1000 127
のように実行します。結果として、127を法の値とした1000の逆元である119が出力されます。今回は、クラスパスの設定がされていません。これは、標準的なインストール状態では、すでにカレントディレクトリ（現在いるディレクトリ）がクラスパスに設定されているためです。もし、カレントディレクトリがクラスパスに含まれてないければ、エラーメッセージが表示されます。


【プログラム名】
 -ModPowBinary.java
 -ModPowBinaryMain.java

　ModPowBinary.javaには、バイナリー法を用いたべき乗剰余計算関数が含まれています。ModPowBinaryMain.javaには、ModPowBinary.java内の関数を使用して、べき乗剰余計算の結果を表示するプログラムが含まれています。

[コンパイル]
パッケージのルートディレクトリで、
>javac modpow\ModPowBinary.java modpow\ModPowBinaryMain.java
を実行することで、"modpow"ディレクトリの下に、"ModPowBinary.class"と"ModPowBinaryMain.class"が生成されます。

[実行]
パッケージのルートディレクトリで、
>java modpow.ModPowBinaryMain 10 20 127
のように実行します。引数を左からa, b, Nに割り当てると、aのb乗をNで割った余りが出力になります。上記の場合は、法の値127の元で10の20乗を計算した値である38が出力されます。


【プログラム名】
 -ModPow2wary.java
 -ModPow2waryMain.java

　ModPow2wary.javaには、2w-ary法を用いたべき乗剰余計算関数が含まれています。ModPow2waryMain.javaには、ModPow2wary.java内の関数を使用して、べき乗剰余計算の結果を表示するプログラムが含まれています。

[コンパイル]
パッケージのルートディレクトリで、
>javac modpow\ModPow2wary.java modpow\ModPow2waryMain.java
を実行することで、"modpow"ディレクトリの下に、"ModPow2wary.class"と"ModPow2waryMain.class"が生成されます。

[実行]
パッケージのルートディレクトリで、
>java modpow.ModPow2waryMain 10 20 127 2
のように実行します。引数を左からa, b, N, wに割り当てると、aのb乗をNで割った余りが出力になります。計算する際のウィンドウサイズはwビットです。上記の場合は、計算をダイビット法で行い、38が出力されます。


【プログラム名】
 -ModPowSlidingWindow.java
 -ModPowSlidingWindowMain.java

　ModPowSlidingWindow.javaには、スライディングウィンドウ法を用いたべき乗剰余計算関数が含まれています。ModPowSlidingWindowMain.javaには、ModPowSlidingWindow.java内の関数を使用して、べき乗剰余計算の結果を表示するプログラムが含まれています。

[コンパイル]
パッケージのルートディレクトリで、
>javac modpow\ModPowSlidingWindow.java modpow\ModPowSlidingWindowMain.java
を実行することで、"modpow"ディレクトリの下に、"ModPowSlidingWindow.class"と"ModPowSlidingWindowMain.class"が生成されます。

[実行]
パッケージのルートディレクトリで、
>java modpow.ModPowSlidingWindowMain 10 20 127 2
のように実行します。引数を左からa, b, N, wに割り当てると、aのb乗をNで割った余りが出力になります。計算する際のウィンドウサイズはwビットです。上記の場合は、計算を2ビット幅のスライディングウィンドウ法で行い、38が出力されます。


【プログラム名】
 -RunModPow.java
 -ModPowSlidingWindow.java

　RunModPow.javaは、スライディングウィンドウ法を用いたべき乗剰余計算速度を評価するためのプログラムです。スライディングウィンドウ法を用いたべき乗剰余計算関数は、ModPowSlidingWindow.javaに含まれていますから、二つをあわせて使用します。

[コンパイル]
パッケージのルートディレクトリで、
>javac modpow\RunModPow.java modpow\ModPowSlidingWindow.java
を実行することで、"modpow"ディレクトリの下に、"RunModPow.class"と"ModPowSlidingWindow.class"が生成されます。

[実行]
パッケージのルートディレクトリで、
>java modpow.RunModPow 2048 10 6
のように実行します。引数は、左から、生成する乱数のビット長、繰り返し回数、ウィンドウサイズとなっています。上記の場合は、2048ビットの乱数に対して、ウィンドウサイズ6ビットのスライディングウィンドウ法で、べき乗剰余計算を10回行い、その処理時間を表示します。使用する計算機の性能が低いと、結果が表示されるまでに数十秒かかる場合があります。


【プログラム名】
 -SignWithCRT.java
 -SignWithCRTMain.java

　SignWithCRT.javaには、中国人剰余定理を利用したべき乗剰余計算関数が含まれています。SignWithCRTMain.javaには、SignWithCRT.java内の関数を用いてべき乗剰余計算結果を表示するプログラムが含まれています。これらのプログラムでは、"modinverse"ディレクトリにあるModInverse.java内のModInverse関数を使用します。

[コンパイル]
パッケージのルートディレクトリで、
>javac modinverse\ModInverse.java crt\SignWithCRT.java crt\SignWithCRTMain.java
を実行することで、"modinverse"ディレクトリ内に"ModInverse.class"が、"crt"ディレクトリ内に"SignWithCRT.class"と"SignWithCRTMain.class"が生成されます。

[実行]
パッケージのルートディレクトリで、
>java crt.SignWithCRTMain 17 7 11 7
のように実行します。引数は、左から、暗号化されたメッセージ、素数p、素数q、秘密指数に対応します。上記の場合は、法の値77(=7x11)の元で17の7乗を計算しますので、結果は52となります。


【プログラム名】
 -FermatTest.java
 -FermatTestMain.java

　FermatTest.javaには、フェルマーテストにより素数判定を行う関数が含まれています。FermatTestMain.javaには、FermatTest.java内の関数を用いて素数判定結果を表示するプログラムが含まれています。FermatTest.javaでは、"modpow"パッケージのModPowBinaryクラスを使用しています。

[コンパイル]
パッケージのルートディレクトリで、
>javac probableprime\FermatTestMain.java
を実行することで、"modpow"ディレクトリ内に"ModPowBinary.class"が、"probableprime"ディレクトリ内に"FermatTest.class"と"FermatTestMain.class"が生成されます。ここで、javacの引数として"modpow\ModPowBinary.java"と"probableprime\FermatTest.java"が記述されていないことに注意してください。実は、javacは、必要なパッケージがあれば、クラスパスから自動的に探し出してきて、コンパイルしてくれるのです。つまり、"FermatTestMain.java"をコンパイルするには、"probableprime"パッケージのFermatTestクラスが必要になるので、コンパイラが自動的にそれを探索し、FermatTest.javaをコンパイルするには、"modpow"パッケージのModPowBinaryクラスが必要になるので、コンパイラが自動的にそれを探索してコンパイルを続行するということになります。今回のように、パッケージのルートディレクトリで作業し、そのカレントディレクトリがクラスパスの設定に含まれていれば、コンパイルに依存するファイルをすべて列挙する必要はないわけです。

[実行]
パッケージのルートディレクトリで、
>java probableprime.FermatTestMain 1021 1
のように実行します。上記の場合、引数1021は素数なので、結果としてtrueが表示されます。二番目の引数は、フェルマーテストの実行回数です。


【プログラム名】
 -MillerRabinTest.java
 -MillerRabinTestMain.java

　MillerRabinTest.javaには、ミラー・ラビンテストにより素数判定を行う関数が含まれています。MillerRabinTestMain.javaには、MillerRabinTest.java内の関数を用いて素数判定結果を表示するプログラムが含まれています。MillerRabinTest.javaでは、"modpow"パッケージのModPowBinaryクラスを使用しています。

[コンパイル]
パッケージのルートディレクトリで、
>javac probableprime\MillerRabinTestMain.java
を実行することで、"modpow"ディレクトリ内に"ModPowBinary.class"が、"probableprime"ディレクトリ内に"MillerRabinTest.class"と"MillerRabinTestMain.class"が生成されます。

[実行]
パッケージのルートディレクトリで、
>java probableprime.MillerRabinTestMain 1021 1
のように実行します。上記の場合、引数1021は素数なので、結果としてtrueが表示されます。二番目の引数は、ミラー・ラビンテストの実行回数です。


3-2. AES関係プログラムの使い方

【プログラム名】
 -AESAlgorithm.java
 -AESOperation.java

　AESAlgorithm.javaには、AES暗号のアルゴリズムを実装した関数と暗号化や復号化を行う関数が含まれています。AESOperation.javaには、ECBモード・CBCモード・CTRモードの各モードを使用して、データブロックを暗号化または復号化する関数が含まれています。

[コンパイル]
パッケージのルートディレクトリで、
>javac aes\AESOperation.java
を実行することで、"aes"ディレクトリの中に"AESAlgorithm.class"と"AESOperation.class"が生成されます。"AESAlgorithm.class"がコンパイルされているのは、先ほども述べたように、Javaコンパイラが自動的に必要なクラスファイルを検索してコンパイルするためで、AESOperation.javaが必要としている"aes\AESAlgorithm.java"が自動的にコンパイルされたのです。（もちろん、これはaes\AESAlgorithm.javaがクラスパスに含まれているからで、クラスパス内にこのファイルが見つからない場合は、コンパイルエラーとなります。）

[実行]
このAESプログラムは、コマンドプロンプトから直接呼び出すための関数が用意されていません。まず、JUnitを使用したテストについて説明します。
　AESAlgorithm.javaとAESOperation.javaの後半には、"@Test"で始まる関数の記述があります。これらは、JUnitという、プログラム機能テスト用のプログラムで使用する関数です。@Testというのは、その宣言にあたります。たとえば、AESAlgorithm.javaには、

  @Test
  public void expandKeyTest128() {
    AESAlgorithm aes = new AESAlgorithm();

    byte[] key = new byte[16];
    for (int i = 0; i < 16; i++)
        key[i] = (byte) 0;
    int[][] roundKeys = aes.expandsKey(key);
    int[][] expectingKeys = new int[][] {
            new int[] { 0x00000000, 0x00000000, 0x00000000, 0x00000000 },
            new int[] { 0x62636363, 0x62636363, 0x62636363, 0x62636363 },
            new int[] { 0x9b9898c9, 0xf9fbfbaa, 0x9b9898c9, 0xf9fbfbaa },
            new int[] { 0x90973450, 0x696ccffa, 0xf2f45733, 0x0b0fac99 },
            new int[] { 0xee06da7b, 0x876a1581, 0x759e42b2, 0x7e91ee2b },
            new int[] { 0x7f2e2b88, 0xf8443e09, 0x8dda7cbb, 0xf34b9290 },
            new int[] { 0xec614b85, 0x1425758c, 0x99ff0937, 0x6ab49ba7 },
            new int[] { 0x21751787, 0x3550620b, 0xacaf6b3c, 0xc61bf09b },
            new int[] { 0x0ef90333, 0x3ba96138, 0x97060a04, 0x511dfa9f },
            new int[] { 0xb1d4d8e2, 0x8a7db9da, 0x1d7bb3de, 0x4c664941 },
            new int[] { 0xb4ef5bcb, 0x3e92e211, 0x23e951cf, 0x6f8f188e } };

    Assert.assertEquals(roundKeys.length, expectingKeys.length);
    for (int i = 0; i < roundKeys.length; i++) {
        Assert.assertEquals(roundKeys[i].length, expectingKeys[i].length);
        for (int j = 0; j < roundKeys[i].length; j++) {
            int k = expectingKeys[i][j];
            int krev = ((k >> 0) & 0xff) << 24 | ((k >> 8) & 0xff) << 16
                    | ((k >> 16) & 0xff) << 8 | ((k >> 24) & 0xff) << 0;
            Assert.assertEquals(roundKeys[i][j], krev);
        }
    }
  }

という記述があります。これは、128ビットの鍵拡張関数をテストするための関数です。中には、expectingKeysという名前の配列で、具体的な数値列が記述されています。配列keyに0を代入していることからもわかるように、この具体的な値は、128ビットの暗号鍵を0x0000000000000000にした場合の、各ラウンド鍵を表しています。Assert.assertEquals(A,B)という形式の記述は、AとBが一致することを期待しているという宣言で、これが満たされない場合（すなわちAとBが異なる場合）にエラーメッセージが出力されます。expandKeyTest128関数では、ラウンド鍵のサイズと値をチェックしています。
　@TestとAssertクラスを使用するために、AESAlgorithm.javaとAESOperation.javaの先頭では、

    import junit.framework.Assert;
    import org.junit.Test;
　
という2行で、それぞれのクラスをインポートしています。

　では、実際にテストを実行して見ましょう。コマンドプロンプトで、

>java org.junit.runner.JUnitCore aes.AESAlgorithm

を実行すると、以下のような結果が出力されます。

JUnit version 4.4
......
Time: 0.691

OK (6 tests)

これは、0.691秒で6つのテストを実行し、結果は合格(OK)だったということです。実際に、AESAlgorithm.javaには、

    expandKeyTest128()  ：鍵拡張部のテスト（128ビット鍵）
    expandKeyTest192()  ：鍵拡張部のテスト（192ビット鍵）
    expandKeyTest256()  ：鍵拡張部のテスト（256ビット鍵）
    mixColumnTest()     ：mixColumn部（暗号化時、復号化時）のテスト
    encryptDecryptTest()：乱数を用いた暗号化、復号化のテスト
    encryptTest()       ：本クラスによる暗号化結果とJavaに実装されているAES処理結果の比較

という6つのテストが実装されています。
　同様にして、AESOpearation.javaの方のテストも行うことができます。

>java org.junit.runner.JUnitCore aes.AESOperation
JUnit version 4.4
...
Time: 2.173

OK (3 tests)

こちらは、2.173秒で3つのテストを実行し、合格しています。AESOperation.javaに実装されている3つのテストは、以下のとおりです。

    ecbTest()   ：ECBモードでの暗号化復号化テスト、およびjava標準実装との比較
    cbcTest()   ：CBCモードでの暗号化復号化テスト、およびjava標準実装との比較
    ctrTest()   ：CTRモードでの暗号化復号化テスト、およびjava標準実装との比較

　ところで、テストコードを見ると、実装したAESコードを自分のプログラムで使うための方法がわかります。AESOperation.javaのecbTest()関数から一部を引用します。左に行番号をつけました。

1 :     byte[] data = new byte[256];
2 :     byte[] key = new byte[16];
3 :     AESOperation aes = new AESOperation();

4 :     for (int i = 0; i < 256; i++) {
5 :         Random r = new Random(System.currentTimeMillis());
6 :         r.nextBytes(key);
7 :         r.nextBytes(data);

8 :         byte[] enc1 = aes.encryptECB(data, key);
9 :         Assert.assertEquals(data.length, enc1.length);

10:         byte[] dec1 = aes.decryptECB(enc1, key);
11:         Assert.assertEquals(data.length, dec1.length);

　1行目は、byte型の配列として、256バイトを確保しています。2行目は、同じくbyte型の配列として、16バイトを確保しています。それぞれ変数名がdata, keyとあるように、dataは入力データ用で、keyは鍵用です。入力データは256バイトに限らずいくつでも良いです。keyは16バイトですから、128ビットということになります。実装したAES関数は、keyのバイト長を見て、自動的にどのビット数の暗号化鍵が入力されたかを判断します。24バイトを確保すれば192ビット鍵モードですし、32バイトを確保すれば256ビット鍵モードとなります。
　3行目は、AESOperationクラスをインスタンス化して、aesという名前の変数に代入しています。AESOpeartion暮らすには、encryptECB()やdecryptECB()などのメソッドが用意されています（詳しくはAESOperation.javaを見てください）。
　4行目からは、テスト用のループ処理になっています。
　5行目では、Randomクラスをインスタンス化してrという変数に代入しています。6行目と7行目では、RandomクラスのnextBytesメソッドを使用して、引数に指定したbyte型変数の長さに応じた数の乱数を格納します。つまり、keyには16バイトの乱数が、dataには256バイトの乱数がそれぞれ生成されて格納されます。
　8行目は、鍵keyでデータdataのECBモード暗号化処理を行い、結果をenc1と名づけたbyte型の配列に格納します。9行目では、AsserクラスのassertEqualsメソッドを使用して、入力データの長さ(256バイト)と暗号化結果の長さが等しい(256バイトである)かどうかを確認しています。
　9行目は、鍵keyでデータenc1のECBモード復号化処理を行い、結果をdec1と名づけられたbyte型の配列に格納します。10行目では、入力データの長さと、復号化結果の長さを比較しています。
　そのほかのCBCモードやCTRモードを使用する場合も、同様の使い方になります。違いはわずかなので、ソースコードを読んでみてください。

　暗号利用モードを通してではなく、直接AESを実行したい場合について見てみます。以下にAESAlgorithm.javaの中から、encryptDecryptTest()関数を引用します。

1 : public void encryptDecryptTest() {
2 :     byte[] data = new byte[16];
3 :     byte[] key = new byte[16];
4 :     byte[] enc = new byte[16];
5 :     byte[] dec = new byte[16];
6 :     AESAlgorithm aes = new AESAlgorithm();

7 :     for (int i = 0; i < 256; i++) {
8 :         Random r = new Random(System.currentTimeMillis());
9 :         r.nextBytes(key);
10:         aes.setKey(key);

11:         r.nextBytes(data);
12:         aes.setBlock(data, 0);
13:         aes.encryptBlock();
14:         aes.getBlock(enc, 0);

15:         aes.setBlock(enc, 0);
16:         aes.decryptBlock();
17:         aes.getBlock(dec, 0);

18:         for (int j = 0; j < 16; j++)
19:             Assert.assertEquals(data[j], dec[j]);
20:     }
21: }

　1行目は、cneryptDecryptTest関数の宣言文です。2行目から5行目では、各16バイトのbyte型配列を作成して、data, key, enc, decにそれぞれ割り当てています。6行目は、AESAlgorithmクラスをインスタンス化して、aesに割り当てています。
　7行目から20行目までは、テストのループ処理です。
　8行目では、Randomクラスをインスタンス化して変数rに代入しています。9行目では、暗号鍵格納用の変数keyに16バイト(keyの配列長)分の乱数を生成して格納し、10行目で、AESAlgorithmクラスのsetKeyメソッドを使用して鍵をセットしています。AESAlgorithm.javaに実装されているAESAlgorithmクラスでは、

    setBlock()      ：データブロックを設定する
    getBlock()      ：現在のデータブロックを読み出す
    setKey()        ：鍵をセットする
    encryptBlock()  ：暗号化を実行する
    decryptBlock()  ：復号化を実行する

といったメソッドが公開されていますので、AESAlgorithm.javaのpublic宣言されている行を確認して見てください。
　続いて11行目では、暗号化する16バイトのデータを乱数で生成して、変数dataに格納しています。12行目では、AESAlgorithmクラスのsetBlockメソッドを使用してデータを設定しています。第二引数の0というのは、offsetを意味しています。これは、第一引数の何バイト目から(16バイトを)読み出すかを指定する値です。暗号利用モードで、16バイトよりも長いデータを暗号化または復号化する場合などに、0以外の値を設定すれば便利です。
　13行目で、実際に暗号化の処理を起動しています。14行目では、暗号化結果をencと名づけた先ほどの変数に代入します。暗号化だけを行いたい場合は、以上で処理を終了しても良いです。
　15行目から17行目は、復号化の処理です。鍵の設定は、10行目で終了しているので繰り返しません。15行目では、先ほどの暗号化結果であるencを入力として設定しています。16行目では、復号化処理を実行しており、その結果は17行目で変数decに格納しています。
　上記のencryptDecryptTest関数は、機能テスト用の関数ですから、18行目と19行目で、復号化結果が入力データに一致しているかどうかを確認しています。



3-3. ハッシュ関数関係プログラムの使い方

【プログラム名】
-SHA1.java

　SHA1.javaには、SHA1によるハッシュ計算関数が含まれています。

[コンパイル]
パッケージのルートディレクトリで、

>javac hash\SHA1.java

を実行することで、"hash"ディレクトリの下に、"SHA1.class"が生成されます。

[実行]
このSHA1プログラムには、コマンドプロンプトから直接呼び出すための関数が用意されていません。JUnitを使用したテストを記述しているので、それについて説明します。
　パッケージのルートディレクトリで、

>java org.junit.runner.JUnitCore hash.SHA1

を実行すれば、JUnitによるテストが行われます。SHA1.javaには、

    test1()     ："The quick brown fox jumps over the lazy dog"のハッシュ結果を既知の値と比較
    test2()     ："The quick brown fox jumps over the lazy cog"のハッシュ結果を既知の値と比較
    test3()     ：""のハッシュ結果を既知の値と比較

という3つのテストが実装されています。たとえばtest1()を見れば、SHA1クラスの使い方がわかります。

1 : public void test1() throws NoSuchAlgorithmException {
2 :     final String text = "The quick brown fox jumps over the lazy dog";
3 :     final int[] hashval = new int[] { 0x2fd4e1c6, 0x7a2d28fc, 0xed849ee1,
4 :             0xbb76e739, 0x1b93eb12 };

5 :     final int[] sha1hash0 = this.hash(text.getBytes());
6 :     Assert.assertTrue(Arrays.equals(sha1hash0, hashval));

　上記のプログラムは、test1()関数の一部です。2行目では、ハッシュ値を計算したい入力文をtestという名前の変数に代入しています。String型に代入していることに注意してください。
　3,4行目では、この入力文に対する正しいハッシュ値を、hashvalというint型の配列に代入しています。
　5行目がハッシュ値の計算です。ここでは、テスト関数をSHA1クラスの中に記述しているので、this.hash(引数)という呼び出し方をしています。自作のプログラムなど、SHA1クラス以外でハッシュ計算をしたい場合は、いったんSHA1クラスをインスタンス化しておいて、その後で、hashメソッドを呼び出すようにします。hashメソッドは、引数としてbyte型の配列を受け取ります。そのため、5行目のコードでは、String型のメソッドであるgetBytes()を用いて、byte型の配列に変換しているのです。計算されたハッシュ値は、int型の配列で戻されます。上記では、これをsha1hash0という名前の配列に代入しています。
　6行目は、事前に知っている正しいハッシュ値と、SHA1クラスで計算したハッシュ値が一致することを確認しています。一致しなければ、エラーメッセージとなります。


【プログラム名】
-SHA256.java

　SHA256.javaには、SHA1によるハッシュ計算関数が含まれています。

[コンパイル]
パッケージのルートディレクトリで、

>javac hash\SHA256.java

を実行することで、"hash"ディレクトリの下に、"SHA256.class"が生成されます。

[実行]
SHA1クラスと同様に、SHA256クラスも、コマンドラインから呼び出すためのハッシュ値計算メソッドを備えていません。テストコードの構成もSHA1クラスと同じになっているので、比較しながら見れば、すぐに使い方を理解することができると思います。


【プログラム名】
-HMAC.java

　HMAC.javaには、指定したハッシュ関数を用いたHMAC計算関数が含まれています。

[コンパイル]
パッケージのルートディレクトリで、

>javac hash\HMAC.java

を実行することで、"hash"ディレクトリの下に、"HMAC.class"が生成されます。

[実行]
HMACクラスには、test()関数として、SHA256を使用して鍵"0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b"に対して"Hi There"という文のHMACを計算する例が実装されています。

>java org.junit.runner.JUnitCore hash.HMAC

のように実行することで、正しい計算結果"b0344c61d8db38535ca8afceaf0bf12b881dc200c9833da726e9376c2e32cff7"が出力されるかどうかのテストを行います。


3-4. SSL関係プログラムの使い方

【プログラム名】
-SSLClient.java
-SSLServer.java

　SSLClient.javaには、Javaの標準実装によるクライアント側からのSSLハンドシェイク実装例が含まれています。また、SSLServer.javaには、サーバー側からのSSLハンドシェイク実装例が含まれています。二つのプログラムをそれぞれ異なるコンピュータで実行すれば、一方をサーバー、もう一方をクライアントとしたSSLハンドシェイクが行われる様子を確認することができます。

[コンパイル]
パッケージの"SSL"ディレクトリで、

>javac SSLtest\SSLClient.java
>javac SSLtest\SSLServer.java

を実行することで、"SSLtest"ディレクトリの下に、"SSLClient.class"と"SSLServer.class"が生成されます。

[実行]
　このSSLテストプログラムを実行するためには、事前にサーバー証明書を作成してサーバー側とクライアント側においておかなければなりません。証明書を生成するために、ここでは、JavaのSDKをインストールすると同時にインストールされる、keytoolというプログラムを使用します。本文中で述べたように、keytoolを使用して、サーバーの証明書serverKeyStoreを作成し、そこから証明書を取り出して、クライアントの信用リストclientTruststoreに保存します。手順は以下のとおりです。

>keytool -genkey -keystore serverKeystore -alias server -keyalg rsa
---名前などのいくつかの情報入力が求められる---

>keytool -export -alias server -keystore serverKeystore -file server.cer
>keytool -import -alias server -keystore clientTruststore -file server.cer

これで、serverKeystoreとserver.cerとclientTruststoreが作成されます。クライアント認証を行う場合には、同じようにして、clientKeystoreとserverTruststoreを作成することができます。ただし、本例では、クライアント認証は行いませんから、後者2つは使用しません。作成したserverKeystoreはサーバ側で、clientTruststoreはクライアント側で保持します。server.cerは、削除してもかまいません。

　ここで、プログラムの簡単な説明を行っておきます。まず、SSLClient.javaです。

        String serverHost = "localhost";

という行の"localhost"というのは、自分自身をあらわしています。他のコンピュータがサーバ機能を持っている場合には、そのサーバのIPアドレスを指定します。ここでは、実験のため、一台のコンピュータでサーバとクライアントの両方を動作させることを想定していますから、localhostを指定しています。接続ポートは、443番に設定しています。サーバ側と同じ番号であれば443番でなくても良いのですが、一部のポート番号は、すでにOSで他の機能に使用されていることもありますから、ここでは一般的なSSL接続のポート番号である443番を用いています。

		String trustStore = "SSLtest/client/clientTruststore";
		System.setProperty("javax.net.ssl.trustStore" , trustStore );
		System.setProperty("javax.net.ssl.trustStorePassword", "asdfghjkl");

これらの行では、trustStoreの読み込みと読み出しのためのパスワードを設定しています。本例は、"SSL"ディレクトリで実行することを想定しています。また、trustStoreは、相対パス"SSLtest/client/clientTrustStore"の位置に保存してあります。"asdfghjkl"というのはパスワードです。テスト用に用意したclientTruststoreのパスワードは、このような簡単なパスワードを設定しましたが、通常はもっと難しいパスワードを指定しておくと良いでしょう。
　次に、SSLServer.javaです。

		String keyStore = "SSLtest/server/serverKeystore";

という部分は、serverKeystoreの場所を指定しています。パスワードの指定は、

			char[] keystorePass = "lkjhgfdsa".toCharArray();
			ks.load( new FileInputStream( keyStore ) , keystorePass );

です。本例では、serverKeystoreのパスワードを"lkjhgfdsa"としました。

　以上で、準備が整いました。コンパイルしたプログラムを実行しましょう。まずは、SSLServerから実行します。SSLClientは、その次です。順序を間違えるとうまく動作しませんから、注意してください。

>java SSLtest.SSLServer
SSL接続を待機しています。

SSLServerを実行すると、待機状態に入ってしまうので、コマンドプロンプトをもうひとつ立ち上げる必要があります。

>java SSLtest.SSLClient
Serverからのメッセージ：Hello Client

というように、実行に成功するとサーバーからのメッセージを受け取ることができます。一方、サーバーを起動していたコマンドプロンプトには、

SSL接続を待機しています。
Clientから接続されました。
Clientからのメッセージ:Hello Server
SSL接続を待機しています。

というようなメッセージが表示されています。クライアント側からもメッセージを送ったからです。サーバーは、引き続きSSL通信を受け付けるために待機しています。


【プログラム名】
-miniSSLc.java
-miniSSLs.java

　miniSSLc.javaには、クライアント側からのSSLハンドシェイク実装例が含まれています。また、miniSSLs.javaには、サーバー側からのSSLハンドシェイク実装例が含まれています。二つのプログラムをそれぞれ異なるコンピュータで実行すれば、一方をサーバー、もう一方をクライアントとしたSSLハンドシェイクが行われる様子を確認することができます。

[コンパイル]
パッケージの"SSL"ディレクトリで、

>javac miniSSL\miniSSLc.java
>javac miniSSL\miniSSLs.java

を実行することで、"miniSSL"ディレクトリの下に、"miniSSLc.class"と"miniSSLs.class"が生成されます。

[実行]
本プログラムを使用するにあたって、サーバー証明書serverKeyStoreとクライアントの受け入れ可能証明書リストclientTrustStoreを用意します。作成方法は、SSLServer.java, SSLClient.javaの紹介の際に述べたので、同様に作成してください。先の例で作成したものをそのまま転用することもできます。本例では、これらのファイルを"SSL/miniSSL"ディレクトリに置いています。
　プログラムの実行は、"SSL"ディレクトリで行います。先に、サーバー側を実行します。

>java miniSSL.miniSSLs
Listening on port 443

続いてクライアント側を実行します。

>java miniSSL.miniSSLc
ClientHello
ServerHello
ServerCertificate
ServerDone
ClientKeyExchange
ChangeCipherSpec
ClientFinished
ChangeCipherSpec from Server
Server Finished OK

というように表示されれば、ハンドシェイクは成功です。本プログラムは、SSLハンドシェイクの流れを説明するためのものです。一般的な接続に対応することができるわけではありませんから、プログラムの流れを追って、仕様書の内容を解読するのに役立ててください。

