========================================================
=
=   Java�ō���Ċw�ԈÍ��Z�p
=
=   �v���O�����̉��
=
========================================================

Java�́AWindows��Linux,MacOS�ȂǑ�����OS�ɑΉ����Ă��܂����A�{�������́AWindowsXP��z�肵�Ă��܂��B���̑���OS���g�p���Ă�����́A�K�v�ɉ����ēǂݑւ��Ă��������B

1. ���̃p�b�P�[�W�̍\��

�@���̃p�b�P�[�W�̍\���́A�ȉ��̂悤�ɂȂ��Ă��܂��B
�@[���O]�Ƃ����\�L�́A�f�B���N�g������\���Ă��܂��B

[���[�g�f�B���N�g��]
README.txt                      �F���܊J���Ă���t�@�C���ł�

[gcd] ���ő���񐔌v�Z��
    GCDTrialDivision.java       �F���X�g2.1
    GCDTrialDivisionMain.java   �F���X�g2.2

    GCDEuclid.java              �F���X�g2.3
    GCDEuclidMain.java          �F�i�R�}���h���C����C���^�[�t�F�[�X�j

    GCDBinaryEuclid.java        �F���X�g2.4
    GCDBinaryEuclidMain.java    �F�i�R�}���h���C����C���^�[�t�F�[�X�j

    RunGCD.java                 �F���X�g2.5

    GCDBigInteger.java          �F���X�g2.6
    GCDBigIntegerMain.java      �F�i�R�}���h���C����C���^�[�t�F�[�X�j
    
[extendedeuclid] ���ő���񐔌v�Z�F�g�����[�N���b�h�ݏ��@��
    ExtendedEuclidGCD.java      �F���X�g2.7
    ExtendedEuclidGCDMain.java  �F���X�g2.8

    ExtededEuclidGCDTest.java   �FJUnit�ɂ��e�X�g�Ŏg�p(GCDBigInteger.class���g�p)
    
[modinverse] ���w���v�Z�i�t���v�Z�j��
    ModInverse.java             �F���X�g2.9
    ModInverseMain.java         �F���X�g2.10
    
[modpow] ���ׂ����]�v�Z��
    ModPowBinary.java           �F���X�g2.11
    ModPowBinaryMain.java       �F���X�g2.12

    ModPow2wary.java            �F���X�g2.13
    ModPow2waryMain.java        �F���X�g2.14

    ModPowSlidingWindow.java    �F���X�g2.15(�O��), 2.16(�㔼)
    ModPowSlidingWindowMain.java�F���X�g2.17

    RunModPow.java              �F���X�g2.18
    
[crt] �������l��]�藝��
    SignWithCRT.java            �F���X�g2.19
    SignWithCRTMain.java        �F���X�g2.20
    
[probableprime] ���f�����聄
    FermatTest.java             �F���X�g3.1
    FermatTestMain.java         �F���X�g3.2

    MillerRabinTest.java        �F���K���p
    MillerRabinTestMain.java    �F���K���p
    
[aes] ��AES�Í���
    AESAlgorithm.java           �F���X�g4.1�`4.8
    AESOperation.java           �F���X�g4.9�`���X�g4.11
    
[hash] ���n�b�V���֐���
    SHA1.java                   �F���X�g5.1�`5.3
    SHA256.java                 �F���X�g5.4�`5.6
    HMAC.java                   �F���X�g5.7
    
[ssl] ��SSL (Secure Socket Layer)��
    [SSLtest]
        SSLClient.java          �F���X�g6.1
        SSLServer.java          �F���X�g6.2
    [miniSSL]
        miniSSLc.java           �F���X�g6.3�`���X�g6.17
        miniSSLs.java           �F���X�g6.18�`���X�g6.22


2. �K�v�ȃc�[���̏���

�@���^�����v���O�������R���p�C������ю��s���邽�߂ɂ́AJava�̃R���p�C�����iDevelopment Kit�j�Ǝ��s���i�o�[�`�����}�V���j���K�v�ł��B�܂��A�ꕔ�̃v���O�����ɂ́AJUnit�Ƃ����v���O�����e�X�g�p�v���O������p�����A�e�X�g�R�[�h���܂܂�Ă��܂��B�����̃v���O�����́AJUnit�ɂ��e�X�g���g�p���Ȃ��ꍇ�ł��AJUnit���C���X�g�[�����Ȃ��ƁA�R���p�C�����邱�Ƃ��ł��܂���iJUnit���C���X�g�[�����Ȃ��ꍇ�́AJUnit�֌W�̋L�q���폜���Ă���R���p�C������K�v������܂��j�B

�@�EJava(TM) 2 SE Development Kit (JDK 5.0)   �FJava�v���O�����̍쐬�Ǝ��s�Ɏg�p���܂��B
�@�EJUnit4                                    �FJava�v���O�����̃e�X�g�Ɏg�p���܂��B

�@JDK 5.0�́A���M���_�ł́A"http://java.sun.com/javase/downloads/index_jdk5.jsp"����_�E�����[�h���邱�Ƃ��ł��܂��BDOWNLOAD�{�^�����N���b�N����ƁA�p��̃y�[�W�ɃW�����v���܂��Blicense agreement��Accept�Ƀ`�F�b�N�}�[�N����ꂽ��ɁAwindows�łł����"jdk-1_5_0_13-windows-i586-p.exe"���_�E�����[�h���Ă��������B�����_�̍ŐV�ł́AUpdate 13�ł��B

�@JDK���C���X�g�[������ƁA�R�}���h�v�����v�g����A"java"��"javac"�Ƃ������R�}���h���g�p���邱�Ƃ��ł���悤�ɂȂ�܂��Bjava�v���O�����́A"javac [�t�@�C����(�g���q�L��)]"�Ƃ��ăR���p�C�����A"java [�t�@�C����(�g���q����)]"�Ƃ��Ď��s����̂���{�ł��B

��j
test.java
�Ƃ����v���O�������쐬�����Ƃ��܂��傤�B
>javac test.java
�����s����ƁA(�R���p�C��������������)test.class�Ƃ����t�@�C������������܂��B����ɁA
>java test
�����s����ƁAtest.class���ǂݍ��܂�āA�쐬�����v���O���������s����܂��B
�i�R�}���h�v�����v�g��ŃR�}���h����͂���ꍇ�A">"�Ƃ����L�����g���܂��B�j

Java�̃o�[�W�����́A�R�}���h���C����
>java -version
�Ɠ��͂���ƁA�\������܂��B

��j
>java -version
java version "1.5.0_11"
Java(TM) 2 Runtime Environment, Standard Edition (build 1.5.0_11-b03)
Java HotSpot(TM) Client VM (build 1.5.0_11-b03, mixed mode, sharing)

�@JUnit4�́A���M���_�ł́A"http://www.junit.org/"����_�E�����[�h���邱�Ƃ��ł��܂��BDownload JUnit���N���b�N���āA�W�����v��̃y�[�W����A"junit-4.4.zip�i���M���_�̍ŐV�Łj"���_�E�����[�h���Ă��������B�����_�̍ŐV�ł́Aversion 4.4�ł��B

�@�_�E�����[�h�����t�@�C�����A�K���ȃt�H���_�ɉ𓀂��܂��B���Ƃ��΁A"C:\Program Files\Java\junit4.4"�ɉ𓀂��܂��B���̏ꍇ�A���ϐ�CLASSPATH�ɂ�"C:\Program Files\Java\junit4.4\junit-4.4.jar;C:\Program Files\Java\junit4.4"��ǉ����Ă����܂��B�C���X�g�[���������������ǂ����́A�ȉ��̗�̂悤�ɂ��Ċm�F���邱�Ƃ��ł��܂��B

��j
>java org.junit.runner.JUnitCore org.junit.tests.AllTests
JUnit version 4.4
...............................................................................................................................................................................................................................................................................I.II.........................................................
Time: 43.503        �i�����v���Ԃ��\������܂��j

OK (329 tests)      �i�����ʂ��\������܂��j


3. �v���O�����̎g����

�@�����ł́A���^����Ă���e�v���O�����̓��e�Ǝg������������܂��B

3-1. RSA�֌W�v���O�����̎g����

�y�v���O�������z
 -GCDTrialDivision.java
 -GCDTrialDivisionMain.java
�@
�@GCDTrialDivision.java�ɂ́A���s����Z�@��p�����ő���񐔌v�Z�֐����܂܂�Ă��܂��B����AGCDTrialDivisionMain.java�ɂ́AGCDTrialDivision.java���̊֐����Ăяo���Čv�Z���ʂ�\������v���O�������܂܂�Ă��܂��B����m�F���s���܂��傤�B

[�R���p�C��]
"gcd"�f�B���N�g���Ɉړ����āA
>javac GCDTrialDivision.java GCDTrialDivisionMain.java
�����s����΁A��̃v���O�������R���p�C�����邱�Ƃ��ł��܂��B�R���p�C�������܂������΁A"GCDTrialDivision.class", "GCDTrialDivisionMain.class"�Ƃ�����̃t�@�C������������܂��BGCDTrialDivisionMain.java�́AGCDTrialDivision.java�̒��g���Q�Ƃ��Ă��܂�����A��𓯎��ɃR���p�C�����Ă��������B

[���s]
"gcd"�f�B���N�g���̒��ŁA
>java -cp .. gcd.GCDTrialDivisionMain 780 660
�����s����΁A780��660�̍ő���񐔂ł���60�����ʂƂ��ĕ\������܂��B�����ŁA"-cp"�Ƃ����̂́A�N���X�p�X��ݒ肷��I�v�V�����ł��B�����ł́A�N���X�p�X�Ƃ��Đe�f�B���N�g��".."���w�肵�Ă��܂��BGCDTrialDivisionMain�́AGCDTrialDivisionMain.java�̐擪�ɋL�q����Ă���悤�ɁA"gcd"�Ƃ������O�̃p�b�P�[�W�̈ꕔ�Ƃ��Ē�`����Ă��܂��B����āAgcd�Ƃ������O�̃p�b�P�[�W����GCDTrialDivisionMain�i�Ƃ������O�̃N���X��main�֐��j�����s���܂��Ƃ����Ӗ��ŁA��L�̂悤�ɋL�q����̂ł��B780��660�́A���̊֐������s����ۂ̈����Ƃ��ēn����܂��B
�@�����ł́A"gcd"�f�B���N�g���̒��Ŏ��s�������߂ɁA�N���X�p�X�̐ݒ��K�v�Ƃ��܂������A�e�f�B���N�g���Ŏ��s����̂ł���΁A"-cp .."�Ƃ����w��͕K�v����܂���B


�y�v���O�������z
 -GCDEuclid.java
 -GCDEuclidMain.java

GCDEuclid.java�ɂ́A���[�N���b�h�ݏ��@��p�����ő���񐔌v�Z�֐����܂܂�Ă��܂��BGCDEuclidMain.java�ɂ́AGCDEuclid.java���̊֐����Ăяo���Čv�Z���ʂ�\������v���O�������܂܂�Ă��܂��B

[�R���p�C��]
"gcd"�f�B���N�g���Ɉړ����āA
>javac GCDEuclid.java GCDEuclidMain.java
�����s���邱�ƂŁA"GCDEuclid.class"��"GCDEuclidMain.class"����������܂��B

[���s]
"gcd"�f�B���N�g���̒��ŁA
>java -cp .. gcd.GCDEuclidMain 780 660
�̂悤�Ɏ��s���܂��B


�y�v���O�������z
 -GCDBinaryEuclid.java
 -GCDBinaryEuclidMain.java

�@GCDBinaryEuclid.java�ɂ́A�o�C�i���[���[�N���b�h�ݏ��@��p�����ő���񐔌v�Z�֐����܂܂�Ă��܂��BGCDBinaryEuclidMain.java�ɂ́AGCDBinaryEuclid.java���̊֐����Ăяo���Čv�Z���ʂ�\������v���O�������܂܂�Ă��܂��B

[�R���p�C��]
"gcd"�f�B���N�g���Ɉړ����āA
>javac GCDBinaryEuclid.java GCDBinaryEuclidMain.java
�����s���邱�ƂŁA"GCDBinaryEuclid.class"��"GCDBinaryEuclidMain.class"����������܂��B

[���s]
"gcd"�f�B���N�g���̒��ŁA
>java -cp .. gcd.GCDBinaryEuclidMain 780 660
�̂悤�Ɏ��s���܂��B


�y�v���O�������z
 -RunGCD.java
 -GCDBinaryEuclid.java

�@RunGCD.java�́A�o�C�i���[���[�N���b�h�ݏ��@��p�����ő���񐔂̌v�Z���x��]�����邽�߂̃v���O�����ł��B�o�C�i���[���[�N���b�h�ݏ��@��p�����ő���񐔌v�Z�֐��́AGCDBinaryEuclid.java�Ɋ܂܂�Ă��܂�����A������킹�Ďg�p���܂��B

[�R���p�C��]
"gcd"�f�B���N�g���Ɉړ����āA
>javac GCDBinaryEuclid.java RunGCD.java
�����s���邱�ƂŁA"GCDBinaryEuclid.class"��"RunGCD.class"����������܂��B

[���s]
"gcd"�f�B���N�g���̒��ŁA
>java -cp .. gcd.RunGCD 1024 15000
�̂悤�Ɏ��s���܂��B���̃v���O�����́A�����̐ݒ�ɂ���ẮA���ʂ��\�������܂łɖc��Ȏ��Ԃ�������܂��̂Œ��ӂ��Ă��������B�i��L�̐ݒ�ł���΁A��r�I�V�����R���s���[�^�ł��A�ЂƂ̌��ʂ�\������̂ɐ��\�b������܂��B�j


�y�v���O�������z
 -GCDBigInteger.java
 -GCDBigIntegerMain.java

�@GCDBigInteger.java�ɂ́AJava��BigInteger�N���X���W���Ŏ������Ă���ő���񐔂̌v�Z�֐����g�p���āA�ő���񐔂��v�Z����v���O�������܂܂�Ă��܂��BGCDBigIntegerMain.java�ɂ́AGCDBigInteger.java���̊֐����Ăяo���Čv�Z���ʂ�\������v���O�������܂܂�Ă��܂��B

[�R���p�C��]
"gcd"�f�B���N�g���Ɉړ����āA
>javac GCDBigInteger.java GCDBigIntegerMain.java
�����s���邱�ƂŁA"GCDBigInteger.class"��"GCDBigIntegerMain.class"����������܂��B

[���s]
"gcd"�f�B���N�g���̒��ŁA
>java -cp .. gcd.GCDBigIntegerMain 780 660
�̂悤�Ɏ��s���܂��B


�y�v���O�������z
 -ExtendedEuclidGCD.java
 -ExtendedEuclidGCDMain.java

�@ExtendedEuclidGCD.java�ɂ́A�g�����[�N���b�h�ݏ��@��p�����ő���񐔌v�Z�֐����܂܂�Ă��܂��BExtendedEuclidGCDMain.java�ɂ́AExtendedEuclidGCD.java���̊֐����Ăяo���Čv�Z���ʂ�\������v���O�������܂܂�Ă��܂��B

[�R���p�C��]
"extendedeuclid"�f�B���N�g���Ɉړ����āA
>javac ExtendedEuclidGCD.java ExtendedEuclidGCDMain.java
�����s���邱�ƂŁA"ExtendedEuclidGCD.class"��"ExtendedEuclidGCDMain.class"����������܂��B

[���s]
"extendedeuclid"�f�B���N�g���̒��ŁA
>java -cp .. extendedeuclid.ExtendedEuclidGCDMain 780 660
�̂悤�Ɏ��s���܂��B�p�b�P�[�W����قǂ�"gcd"����"extendedeuclid"�ɕς��܂����̂ŁA���s���̎w����@���ς���Ă��邱�Ƃɒ��ӂ��Ă��������B���s�̌��ʁA�ő���񐔂ƈꏏ�ɁAax+by=gcd(a,b)�𖞂���x,y���o�͂���܂��ia,b�͓��͒l�ł��B��L�̏ꍇ�Aa=780,b=660�ƂȂ�܂��j�B


�y�v���O�������z
 -ModInverse.java
 -ModInverseMain.java

�@ModInverse.java�ɂ́A�w���v�Z�i�t���v�Z�j�֐����܂܂�Ă��܂��BModInverseMain.java�ɂ́AModInverse.java���̊֐����Ăяo���Ďw���v�Z�i�t���v�Z�j���ʂ�\������v���O�������܂܂�Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���Ɉړ����āA
>javac modinverse\ModInverse.java modinverse\ModInverseMain.java
�����s���邱�ƂŁA"modinverse"�f�B���N�g���̉��ɁA"ModInverse.class"��"ModInverseMain.class"����������܂��B��قǂƈقȂ�A�p�b�P�[�W�̃��[�g�f�B���N�g���Ŏ��s���Ă���Ƃ���ɒ��ӂ��Ă��������B���낻��R���p�C�����@�ɂ����ꂽ�Ǝv���̂ŁA�قȂ�Ăяo���������Ă��܂��B���́A����܂ł̃v���O���������l�ɁA�p�b�P�[�W�̃��[�g�f�B���N�g������R���p�C���Ǝ��s���s�����Ƃ��ł��܂��B

[���s]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>java modinverse.ModInverseMain 1000 127
�̂悤�Ɏ��s���܂��B���ʂƂ��āA127��@�̒l�Ƃ���1000�̋t���ł���119���o�͂���܂��B����́A�N���X�p�X�̐ݒ肪����Ă��܂���B����́A�W���I�ȃC���X�g�[����Ԃł́A���łɃJ�����g�f�B���N�g���i���݂���f�B���N�g���j���N���X�p�X�ɐݒ肳��Ă��邽�߂ł��B�����A�J�����g�f�B���N�g�����N���X�p�X�Ɋ܂܂�ĂȂ�����΁A�G���[���b�Z�[�W���\������܂��B


�y�v���O�������z
 -ModPowBinary.java
 -ModPowBinaryMain.java

�@ModPowBinary.java�ɂ́A�o�C�i���[�@��p�����ׂ����]�v�Z�֐����܂܂�Ă��܂��BModPowBinaryMain.java�ɂ́AModPowBinary.java���̊֐����g�p���āA�ׂ����]�v�Z�̌��ʂ�\������v���O�������܂܂�Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>javac modpow\ModPowBinary.java modpow\ModPowBinaryMain.java
�����s���邱�ƂŁA"modpow"�f�B���N�g���̉��ɁA"ModPowBinary.class"��"ModPowBinaryMain.class"����������܂��B

[���s]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>java modpow.ModPowBinaryMain 10 20 127
�̂悤�Ɏ��s���܂��B������������a, b, N�Ɋ��蓖�Ă�ƁAa��b���N�Ŋ������]�肪�o�͂ɂȂ�܂��B��L�̏ꍇ�́A�@�̒l127�̌���10��20����v�Z�����l�ł���38���o�͂���܂��B


�y�v���O�������z
 -ModPow2wary.java
 -ModPow2waryMain.java

�@ModPow2wary.java�ɂ́A2w-ary�@��p�����ׂ����]�v�Z�֐����܂܂�Ă��܂��BModPow2waryMain.java�ɂ́AModPow2wary.java���̊֐����g�p���āA�ׂ����]�v�Z�̌��ʂ�\������v���O�������܂܂�Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>javac modpow\ModPow2wary.java modpow\ModPow2waryMain.java
�����s���邱�ƂŁA"modpow"�f�B���N�g���̉��ɁA"ModPow2wary.class"��"ModPow2waryMain.class"����������܂��B

[���s]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>java modpow.ModPow2waryMain 10 20 127 2
�̂悤�Ɏ��s���܂��B������������a, b, N, w�Ɋ��蓖�Ă�ƁAa��b���N�Ŋ������]�肪�o�͂ɂȂ�܂��B�v�Z����ۂ̃E�B���h�E�T�C�Y��w�r�b�g�ł��B��L�̏ꍇ�́A�v�Z���_�C�r�b�g�@�ōs���A38���o�͂���܂��B


�y�v���O�������z
 -ModPowSlidingWindow.java
 -ModPowSlidingWindowMain.java

�@ModPowSlidingWindow.java�ɂ́A�X���C�f�B���O�E�B���h�E�@��p�����ׂ����]�v�Z�֐����܂܂�Ă��܂��BModPowSlidingWindowMain.java�ɂ́AModPowSlidingWindow.java���̊֐����g�p���āA�ׂ����]�v�Z�̌��ʂ�\������v���O�������܂܂�Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>javac modpow\ModPowSlidingWindow.java modpow\ModPowSlidingWindowMain.java
�����s���邱�ƂŁA"modpow"�f�B���N�g���̉��ɁA"ModPowSlidingWindow.class"��"ModPowSlidingWindowMain.class"����������܂��B

[���s]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>java modpow.ModPowSlidingWindowMain 10 20 127 2
�̂悤�Ɏ��s���܂��B������������a, b, N, w�Ɋ��蓖�Ă�ƁAa��b���N�Ŋ������]�肪�o�͂ɂȂ�܂��B�v�Z����ۂ̃E�B���h�E�T�C�Y��w�r�b�g�ł��B��L�̏ꍇ�́A�v�Z��2�r�b�g���̃X���C�f�B���O�E�B���h�E�@�ōs���A38���o�͂���܂��B


�y�v���O�������z
 -RunModPow.java
 -ModPowSlidingWindow.java

�@RunModPow.java�́A�X���C�f�B���O�E�B���h�E�@��p�����ׂ����]�v�Z���x��]�����邽�߂̃v���O�����ł��B�X���C�f�B���O�E�B���h�E�@��p�����ׂ����]�v�Z�֐��́AModPowSlidingWindow.java�Ɋ܂܂�Ă��܂�����A������킹�Ďg�p���܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>javac modpow\RunModPow.java modpow\ModPowSlidingWindow.java
�����s���邱�ƂŁA"modpow"�f�B���N�g���̉��ɁA"RunModPow.class"��"ModPowSlidingWindow.class"����������܂��B

[���s]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>java modpow.RunModPow 2048 10 6
�̂悤�Ɏ��s���܂��B�����́A������A�������闐���̃r�b�g���A�J��Ԃ��񐔁A�E�B���h�E�T�C�Y�ƂȂ��Ă��܂��B��L�̏ꍇ�́A2048�r�b�g�̗����ɑ΂��āA�E�B���h�E�T�C�Y6�r�b�g�̃X���C�f�B���O�E�B���h�E�@�ŁA�ׂ����]�v�Z��10��s���A���̏������Ԃ�\�����܂��B�g�p����v�Z�@�̐��\���Ⴂ�ƁA���ʂ��\�������܂łɐ��\�b������ꍇ������܂��B


�y�v���O�������z
 -SignWithCRT.java
 -SignWithCRTMain.java

�@SignWithCRT.java�ɂ́A�����l��]�藝�𗘗p�����ׂ����]�v�Z�֐����܂܂�Ă��܂��BSignWithCRTMain.java�ɂ́ASignWithCRT.java���̊֐���p���Ăׂ����]�v�Z���ʂ�\������v���O�������܂܂�Ă��܂��B�����̃v���O�����ł́A"modinverse"�f�B���N�g���ɂ���ModInverse.java����ModInverse�֐����g�p���܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>javac modinverse\ModInverse.java crt\SignWithCRT.java crt\SignWithCRTMain.java
�����s���邱�ƂŁA"modinverse"�f�B���N�g������"ModInverse.class"���A"crt"�f�B���N�g������"SignWithCRT.class"��"SignWithCRTMain.class"����������܂��B

[���s]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>java crt.SignWithCRTMain 17 7 11 7
�̂悤�Ɏ��s���܂��B�����́A������A�Í������ꂽ���b�Z�[�W�A�f��p�A�f��q�A�閧�w���ɑΉ����܂��B��L�̏ꍇ�́A�@�̒l77(=7x11)�̌���17��7����v�Z���܂��̂ŁA���ʂ�52�ƂȂ�܂��B


�y�v���O�������z
 -FermatTest.java
 -FermatTestMain.java

�@FermatTest.java�ɂ́A�t�F���}�[�e�X�g�ɂ��f��������s���֐����܂܂�Ă��܂��BFermatTestMain.java�ɂ́AFermatTest.java���̊֐���p���đf�����茋�ʂ�\������v���O�������܂܂�Ă��܂��BFermatTest.java�ł́A"modpow"�p�b�P�[�W��ModPowBinary�N���X���g�p���Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>javac probableprime\FermatTestMain.java
�����s���邱�ƂŁA"modpow"�f�B���N�g������"ModPowBinary.class"���A"probableprime"�f�B���N�g������"FermatTest.class"��"FermatTestMain.class"����������܂��B�����ŁAjavac�̈����Ƃ���"modpow\ModPowBinary.java"��"probableprime\FermatTest.java"���L�q����Ă��Ȃ����Ƃɒ��ӂ��Ă��������B���́Ajavac�́A�K�v�ȃp�b�P�[�W������΁A�N���X�p�X���玩���I�ɒT���o���Ă��āA�R���p�C�����Ă����̂ł��B�܂�A"FermatTestMain.java"���R���p�C������ɂ́A"probableprime"�p�b�P�[�W��FermatTest�N���X���K�v�ɂȂ�̂ŁA�R���p�C���������I�ɂ����T�����AFermatTest.java���R���p�C������ɂ́A"modpow"�p�b�P�[�W��ModPowBinary�N���X���K�v�ɂȂ�̂ŁA�R���p�C���������I�ɂ����T�����ăR���p�C���𑱍s����Ƃ������ƂɂȂ�܂��B����̂悤�ɁA�p�b�P�[�W�̃��[�g�f�B���N�g���ō�Ƃ��A���̃J�����g�f�B���N�g�����N���X�p�X�̐ݒ�Ɋ܂܂�Ă���΁A�R���p�C���Ɉˑ�����t�@�C�������ׂė񋓂���K�v�͂Ȃ��킯�ł��B

[���s]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>java probableprime.FermatTestMain 1021 1
�̂悤�Ɏ��s���܂��B��L�̏ꍇ�A����1021�͑f���Ȃ̂ŁA���ʂƂ���true���\������܂��B��Ԗڂ̈����́A�t�F���}�[�e�X�g�̎��s�񐔂ł��B


�y�v���O�������z
 -MillerRabinTest.java
 -MillerRabinTestMain.java

�@MillerRabinTest.java�ɂ́A�~���[�E���r���e�X�g�ɂ��f��������s���֐����܂܂�Ă��܂��BMillerRabinTestMain.java�ɂ́AMillerRabinTest.java���̊֐���p���đf�����茋�ʂ�\������v���O�������܂܂�Ă��܂��BMillerRabinTest.java�ł́A"modpow"�p�b�P�[�W��ModPowBinary�N���X���g�p���Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>javac probableprime\MillerRabinTestMain.java
�����s���邱�ƂŁA"modpow"�f�B���N�g������"ModPowBinary.class"���A"probableprime"�f�B���N�g������"MillerRabinTest.class"��"MillerRabinTestMain.class"����������܂��B

[���s]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>java probableprime.MillerRabinTestMain 1021 1
�̂悤�Ɏ��s���܂��B��L�̏ꍇ�A����1021�͑f���Ȃ̂ŁA���ʂƂ���true���\������܂��B��Ԗڂ̈����́A�~���[�E���r���e�X�g�̎��s�񐔂ł��B


3-2. AES�֌W�v���O�����̎g����

�y�v���O�������z
 -AESAlgorithm.java
 -AESOperation.java

�@AESAlgorithm.java�ɂ́AAES�Í��̃A���S���Y�������������֐��ƈÍ����╜�������s���֐����܂܂�Ă��܂��BAESOperation.java�ɂ́AECB���[�h�ECBC���[�h�ECTR���[�h�̊e���[�h���g�p���āA�f�[�^�u���b�N���Í����܂��͕���������֐����܂܂�Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA
>javac aes\AESOperation.java
�����s���邱�ƂŁA"aes"�f�B���N�g���̒���"AESAlgorithm.class"��"AESOperation.class"����������܂��B"AESAlgorithm.class"���R���p�C������Ă���̂́A��قǂ��q�ׂ��悤�ɁAJava�R���p�C���������I�ɕK�v�ȃN���X�t�@�C�����������ăR���p�C�����邽�߂ŁAAESOperation.java���K�v�Ƃ��Ă���"aes\AESAlgorithm.java"�������I�ɃR���p�C�����ꂽ�̂ł��B�i�������A�����aes\AESAlgorithm.java���N���X�p�X�Ɋ܂܂�Ă��邩��ŁA�N���X�p�X���ɂ��̃t�@�C����������Ȃ��ꍇ�́A�R���p�C���G���[�ƂȂ�܂��B�j

[���s]
����AES�v���O�����́A�R�}���h�v�����v�g���璼�ڌĂяo�����߂̊֐����p�ӂ���Ă��܂���B�܂��AJUnit���g�p�����e�X�g�ɂ��Đ������܂��B
�@AESAlgorithm.java��AESOperation.java�̌㔼�ɂ́A"@Test"�Ŏn�܂�֐��̋L�q������܂��B�����́AJUnit�Ƃ����A�v���O�����@�\�e�X�g�p�̃v���O�����Ŏg�p����֐��ł��B@Test�Ƃ����̂́A���̐錾�ɂ�����܂��B���Ƃ��΁AAESAlgorithm.java�ɂ́A

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

�Ƃ����L�q������܂��B����́A128�r�b�g�̌��g���֐����e�X�g���邽�߂̊֐��ł��B���ɂ́AexpectingKeys�Ƃ������O�̔z��ŁA��̓I�Ȑ��l�񂪋L�q����Ă��܂��B�z��key��0�������Ă��邱�Ƃ�����킩��悤�ɁA���̋�̓I�Ȓl�́A128�r�b�g�̈Í�����0x0000000000000000�ɂ����ꍇ�́A�e���E���h����\���Ă��܂��BAssert.assertEquals(A,B)�Ƃ����`���̋L�q�́AA��B����v���邱�Ƃ����҂��Ă���Ƃ����錾�ŁA���ꂪ��������Ȃ��ꍇ�i���Ȃ킿A��B���قȂ�ꍇ�j�ɃG���[���b�Z�[�W���o�͂���܂��BexpandKeyTest128�֐��ł́A���E���h���̃T�C�Y�ƒl���`�F�b�N���Ă��܂��B
�@@Test��Assert�N���X���g�p���邽�߂ɁAAESAlgorithm.java��AESOperation.java�̐擪�ł́A

    import junit.framework.Assert;
    import org.junit.Test;
�@
�Ƃ���2�s�ŁA���ꂼ��̃N���X���C���|�[�g���Ă��܂��B

�@�ł́A���ۂɃe�X�g�����s���Č��܂��傤�B�R�}���h�v�����v�g�ŁA

>java org.junit.runner.JUnitCore aes.AESAlgorithm

�����s����ƁA�ȉ��̂悤�Ȍ��ʂ��o�͂���܂��B

JUnit version 4.4
......
Time: 0.691

OK (6 tests)

����́A0.691�b��6�̃e�X�g�����s���A���ʂ͍��i(OK)�������Ƃ������Ƃł��B���ۂɁAAESAlgorithm.java�ɂ́A

    expandKeyTest128()  �F���g�����̃e�X�g�i128�r�b�g���j
    expandKeyTest192()  �F���g�����̃e�X�g�i192�r�b�g���j
    expandKeyTest256()  �F���g�����̃e�X�g�i256�r�b�g���j
    mixColumnTest()     �FmixColumn���i�Í������A���������j�̃e�X�g
    encryptDecryptTest()�F������p�����Í����A�������̃e�X�g
    encryptTest()       �F�{�N���X�ɂ��Í������ʂ�Java�Ɏ�������Ă���AES�������ʂ̔�r

�Ƃ���6�̃e�X�g����������Ă��܂��B
�@���l�ɂ��āAAESOpearation.java�̕��̃e�X�g���s�����Ƃ��ł��܂��B

>java org.junit.runner.JUnitCore aes.AESOperation
JUnit version 4.4
...
Time: 2.173

OK (3 tests)

������́A2.173�b��3�̃e�X�g�����s���A���i���Ă��܂��BAESOperation.java�Ɏ�������Ă���3�̃e�X�g�́A�ȉ��̂Ƃ���ł��B

    ecbTest()   �FECB���[�h�ł̈Í����������e�X�g�A�����java�W�������Ƃ̔�r
    cbcTest()   �FCBC���[�h�ł̈Í����������e�X�g�A�����java�W�������Ƃ̔�r
    ctrTest()   �FCTR���[�h�ł̈Í����������e�X�g�A�����java�W�������Ƃ̔�r

�@�Ƃ���ŁA�e�X�g�R�[�h������ƁA��������AES�R�[�h�������̃v���O�����Ŏg�����߂̕��@���킩��܂��BAESOperation.java��ecbTest()�֐�����ꕔ�����p���܂��B���ɍs�ԍ������܂����B

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

�@1�s�ڂ́Abyte�^�̔z��Ƃ��āA256�o�C�g���m�ۂ��Ă��܂��B2�s�ڂ́A������byte�^�̔z��Ƃ��āA16�o�C�g���m�ۂ��Ă��܂��B���ꂼ��ϐ�����data, key�Ƃ���悤�ɁAdata�͓��̓f�[�^�p�ŁAkey�͌��p�ł��B���̓f�[�^��256�o�C�g�Ɍ��炸�����ł��ǂ��ł��Bkey��16�o�C�g�ł�����A128�r�b�g�Ƃ������ƂɂȂ�܂��B��������AES�֐��́Akey�̃o�C�g�������āA�����I�ɂǂ̃r�b�g���̈Í����������͂��ꂽ���𔻒f���܂��B24�o�C�g���m�ۂ����192�r�b�g�����[�h�ł����A32�o�C�g���m�ۂ����256�r�b�g�����[�h�ƂȂ�܂��B
�@3�s�ڂ́AAESOperation�N���X���C���X�^���X�����āAaes�Ƃ������O�̕ϐ��ɑ�����Ă��܂��BAESOpeartion��炷�ɂ́AencryptECB()��decryptECB()�Ȃǂ̃��\�b�h���p�ӂ���Ă��܂��i�ڂ�����AESOperation.java�����Ă��������j�B
�@4�s�ڂ���́A�e�X�g�p�̃��[�v�����ɂȂ��Ă��܂��B
�@5�s�ڂł́ARandom�N���X���C���X�^���X������r�Ƃ����ϐ��ɑ�����Ă��܂��B6�s�ڂ�7�s�ڂł́ARandom�N���X��nextBytes���\�b�h���g�p���āA�����Ɏw�肵��byte�^�ϐ��̒����ɉ��������̗������i�[���܂��B�܂�Akey�ɂ�16�o�C�g�̗������Adata�ɂ�256�o�C�g�̗��������ꂼ�ꐶ������Ċi�[����܂��B
�@8�s�ڂ́A��key�Ńf�[�^data��ECB���[�h�Í����������s���A���ʂ�enc1�Ɩ��Â���byte�^�̔z��Ɋi�[���܂��B9�s�ڂł́AAsser�N���X��assertEquals���\�b�h���g�p���āA���̓f�[�^�̒���(256�o�C�g)�ƈÍ������ʂ̒�����������(256�o�C�g�ł���)���ǂ������m�F���Ă��܂��B
�@9�s�ڂ́A��key�Ńf�[�^enc1��ECB���[�h�������������s���A���ʂ�dec1�Ɩ��Â���ꂽbyte�^�̔z��Ɋi�[���܂��B10�s�ڂł́A���̓f�[�^�̒����ƁA���������ʂ̒������r���Ă��܂��B
�@���̂ق���CBC���[�h��CTR���[�h���g�p����ꍇ���A���l�̎g�����ɂȂ�܂��B�Ⴂ�͂킸���Ȃ̂ŁA�\�[�X�R�[�h��ǂ�ł݂Ă��������B

�@�Í����p���[�h��ʂ��Ăł͂Ȃ��A����AES�����s�������ꍇ�ɂ��Č��Ă݂܂��B�ȉ���AESAlgorithm.java�̒�����AencryptDecryptTest()�֐������p���܂��B

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

�@1�s�ڂ́AcneryptDecryptTest�֐��̐錾���ł��B2�s�ڂ���5�s�ڂł́A�e16�o�C�g��byte�^�z����쐬���āAdata, key, enc, dec�ɂ��ꂼ�ꊄ�蓖�ĂĂ��܂��B6�s�ڂ́AAESAlgorithm�N���X���C���X�^���X�����āAaes�Ɋ��蓖�ĂĂ��܂��B
�@7�s�ڂ���20�s�ڂ܂ł́A�e�X�g�̃��[�v�����ł��B
�@8�s�ڂł́ARandom�N���X���C���X�^���X�����ĕϐ�r�ɑ�����Ă��܂��B9�s�ڂł́A�Í����i�[�p�̕ϐ�key��16�o�C�g(key�̔z��)���̗����𐶐����Ċi�[���A10�s�ڂŁAAESAlgorithm�N���X��setKey���\�b�h���g�p���Č����Z�b�g���Ă��܂��BAESAlgorithm.java�Ɏ�������Ă���AESAlgorithm�N���X�ł́A

    setBlock()      �F�f�[�^�u���b�N��ݒ肷��
    getBlock()      �F���݂̃f�[�^�u���b�N��ǂݏo��
    setKey()        �F�����Z�b�g����
    encryptBlock()  �F�Í��������s����
    decryptBlock()  �F�����������s����

�Ƃ��������\�b�h�����J����Ă��܂��̂ŁAAESAlgorithm.java��public�錾����Ă���s���m�F���Č��Ă��������B
�@������11�s�ڂł́A�Í�������16�o�C�g�̃f�[�^�𗐐��Ő������āA�ϐ�data�Ɋi�[���Ă��܂��B12�s�ڂł́AAESAlgorithm�N���X��setBlock���\�b�h���g�p���ăf�[�^��ݒ肵�Ă��܂��B��������0�Ƃ����̂́Aoffset���Ӗ����Ă��܂��B����́A�������̉��o�C�g�ڂ���(16�o�C�g��)�ǂݏo�������w�肷��l�ł��B�Í����p���[�h�ŁA16�o�C�g���������f�[�^���Í����܂��͕���������ꍇ�ȂǂɁA0�ȊO�̒l��ݒ肷��Ε֗��ł��B
�@13�s�ڂŁA���ۂɈÍ����̏������N�����Ă��܂��B14�s�ڂł́A�Í������ʂ�enc�Ɩ��Â�����قǂ̕ϐ��ɑ�����܂��B�Í����������s�������ꍇ�́A�ȏ�ŏ������I�����Ă��ǂ��ł��B
�@15�s�ڂ���17�s�ڂ́A�������̏����ł��B���̐ݒ�́A10�s�ڂŏI�����Ă���̂ŌJ��Ԃ��܂���B15�s�ڂł́A��قǂ̈Í������ʂł���enc����͂Ƃ��Đݒ肵�Ă��܂��B16�s�ڂł́A���������������s���Ă���A���̌��ʂ�17�s�ڂŕϐ�dec�Ɋi�[���Ă��܂��B
�@��L��encryptDecryptTest�֐��́A�@�\�e�X�g�p�̊֐��ł�����A18�s�ڂ�19�s�ڂŁA���������ʂ����̓f�[�^�Ɉ�v���Ă��邩�ǂ������m�F���Ă��܂��B



3-3. �n�b�V���֐��֌W�v���O�����̎g����

�y�v���O�������z
-SHA1.java

�@SHA1.java�ɂ́ASHA1�ɂ��n�b�V���v�Z�֐����܂܂�Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA

>javac hash\SHA1.java

�����s���邱�ƂŁA"hash"�f�B���N�g���̉��ɁA"SHA1.class"����������܂��B

[���s]
����SHA1�v���O�����ɂ́A�R�}���h�v�����v�g���璼�ڌĂяo�����߂̊֐����p�ӂ���Ă��܂���BJUnit���g�p�����e�X�g���L�q���Ă���̂ŁA����ɂ��Đ������܂��B
�@�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA

>java org.junit.runner.JUnitCore hash.SHA1

�����s����΁AJUnit�ɂ��e�X�g���s���܂��BSHA1.java�ɂ́A

    test1()     �F"The quick brown fox jumps over the lazy dog"�̃n�b�V�����ʂ����m�̒l�Ɣ�r
    test2()     �F"The quick brown fox jumps over the lazy cog"�̃n�b�V�����ʂ����m�̒l�Ɣ�r
    test3()     �F""�̃n�b�V�����ʂ����m�̒l�Ɣ�r

�Ƃ���3�̃e�X�g����������Ă��܂��B���Ƃ���test1()������΁ASHA1�N���X�̎g�������킩��܂��B

1 : public void test1() throws NoSuchAlgorithmException {
2 :     final String text = "The quick brown fox jumps over the lazy dog";
3 :     final int[] hashval = new int[] { 0x2fd4e1c6, 0x7a2d28fc, 0xed849ee1,
4 :             0xbb76e739, 0x1b93eb12 };

5 :     final int[] sha1hash0 = this.hash(text.getBytes());
6 :     Assert.assertTrue(Arrays.equals(sha1hash0, hashval));

�@��L�̃v���O�����́Atest1()�֐��̈ꕔ�ł��B2�s�ڂł́A�n�b�V���l���v�Z���������͕���test�Ƃ������O�̕ϐ��ɑ�����Ă��܂��BString�^�ɑ�����Ă��邱�Ƃɒ��ӂ��Ă��������B
�@3,4�s�ڂł́A���̓��͕��ɑ΂��鐳�����n�b�V���l���Ahashval�Ƃ���int�^�̔z��ɑ�����Ă��܂��B
�@5�s�ڂ��n�b�V���l�̌v�Z�ł��B�����ł́A�e�X�g�֐���SHA1�N���X�̒��ɋL�q���Ă���̂ŁAthis.hash(����)�Ƃ����Ăяo���������Ă��܂��B����̃v���O�����ȂǁASHA1�N���X�ȊO�Ńn�b�V���v�Z���������ꍇ�́A��������SHA1�N���X���C���X�^���X�����Ă����āA���̌�ŁAhash���\�b�h���Ăяo���悤�ɂ��܂��Bhash���\�b�h�́A�����Ƃ���byte�^�̔z����󂯎��܂��B���̂��߁A5�s�ڂ̃R�[�h�ł́AString�^�̃��\�b�h�ł���getBytes()��p���āAbyte�^�̔z��ɕϊ����Ă���̂ł��B�v�Z���ꂽ�n�b�V���l�́Aint�^�̔z��Ŗ߂���܂��B��L�ł́A�����sha1hash0�Ƃ������O�̔z��ɑ�����Ă��܂��B
�@6�s�ڂ́A���O�ɒm���Ă��鐳�����n�b�V���l�ƁASHA1�N���X�Ōv�Z�����n�b�V���l����v���邱�Ƃ��m�F���Ă��܂��B��v���Ȃ���΁A�G���[���b�Z�[�W�ƂȂ�܂��B


�y�v���O�������z
-SHA256.java

�@SHA256.java�ɂ́ASHA1�ɂ��n�b�V���v�Z�֐����܂܂�Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA

>javac hash\SHA256.java

�����s���邱�ƂŁA"hash"�f�B���N�g���̉��ɁA"SHA256.class"����������܂��B

[���s]
SHA1�N���X�Ɠ��l�ɁASHA256�N���X���A�R�}���h���C������Ăяo�����߂̃n�b�V���l�v�Z���\�b�h������Ă��܂���B�e�X�g�R�[�h�̍\����SHA1�N���X�Ɠ����ɂȂ��Ă���̂ŁA��r���Ȃ��猩��΁A�����Ɏg�����𗝉����邱�Ƃ��ł���Ǝv���܂��B


�y�v���O�������z
-HMAC.java

�@HMAC.java�ɂ́A�w�肵���n�b�V���֐���p����HMAC�v�Z�֐����܂܂�Ă��܂��B

[�R���p�C��]
�p�b�P�[�W�̃��[�g�f�B���N�g���ŁA

>javac hash\HMAC.java

�����s���邱�ƂŁA"hash"�f�B���N�g���̉��ɁA"HMAC.class"����������܂��B

[���s]
HMAC�N���X�ɂ́Atest()�֐��Ƃ��āASHA256���g�p���Č�"0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b"�ɑ΂���"Hi There"�Ƃ�������HMAC���v�Z����Ⴊ��������Ă��܂��B

>java org.junit.runner.JUnitCore hash.HMAC

�̂悤�Ɏ��s���邱�ƂŁA�������v�Z����"b0344c61d8db38535ca8afceaf0bf12b881dc200c9833da726e9376c2e32cff7"���o�͂���邩�ǂ����̃e�X�g���s���܂��B


3-4. SSL�֌W�v���O�����̎g����

�y�v���O�������z
-SSLClient.java
-SSLServer.java

�@SSLClient.java�ɂ́AJava�̕W�������ɂ��N���C�A���g�������SSL�n���h�V�F�C�N�����Ⴊ�܂܂�Ă��܂��B�܂��ASSLServer.java�ɂ́A�T�[�o�[�������SSL�n���h�V�F�C�N�����Ⴊ�܂܂�Ă��܂��B��̃v���O���������ꂼ��قȂ�R���s���[�^�Ŏ��s����΁A������T�[�o�[�A����������N���C�A���g�Ƃ���SSL�n���h�V�F�C�N���s����l�q���m�F���邱�Ƃ��ł��܂��B

[�R���p�C��]
�p�b�P�[�W��"SSL"�f�B���N�g���ŁA

>javac SSLtest\SSLClient.java
>javac SSLtest\SSLServer.java

�����s���邱�ƂŁA"SSLtest"�f�B���N�g���̉��ɁA"SSLClient.class"��"SSLServer.class"����������܂��B

[���s]
�@����SSL�e�X�g�v���O���������s���邽�߂ɂ́A���O�ɃT�[�o�[�ؖ������쐬���ăT�[�o�[���ƃN���C�A���g���ɂ����Ă����Ȃ���΂Ȃ�܂���B�ؖ����𐶐����邽�߂ɁA�����ł́AJava��SDK���C���X�g�[������Ɠ����ɃC���X�g�[�������Akeytool�Ƃ����v���O�������g�p���܂��B�{�����ŏq�ׂ��悤�ɁAkeytool���g�p���āA�T�[�o�[�̏ؖ���serverKeyStore���쐬���A��������ؖ��������o���āA�N���C�A���g�̐M�p���X�gclientTruststore�ɕۑ����܂��B�菇�͈ȉ��̂Ƃ���ł��B

>keytool -genkey -keystore serverKeystore -alias server -keyalg rsa
---���O�Ȃǂ̂������̏����͂����߂���---

>keytool -export -alias server -keystore serverKeystore -file server.cer
>keytool -import -alias server -keystore clientTruststore -file server.cer

����ŁAserverKeystore��server.cer��clientTruststore���쐬����܂��B�N���C�A���g�F�؂��s���ꍇ�ɂ́A�����悤�ɂ��āAclientKeystore��serverTruststore���쐬���邱�Ƃ��ł��܂��B�������A�{��ł́A�N���C�A���g�F�؂͍s���܂��񂩂�A���2�͎g�p���܂���B�쐬����serverKeystore�̓T�[�o���ŁAclientTruststore�̓N���C�A���g���ŕێ����܂��Bserver.cer�́A�폜���Ă����܂��܂���B

�@�����ŁA�v���O�����̊ȒP�Ȑ������s���Ă����܂��B�܂��ASSLClient.java�ł��B

        String serverHost = "localhost";

�Ƃ����s��"localhost"�Ƃ����̂́A�������g������킵�Ă��܂��B���̃R���s���[�^���T�[�o�@�\�������Ă���ꍇ�ɂ́A���̃T�[�o��IP�A�h���X���w�肵�܂��B�����ł́A�����̂��߁A���̃R���s���[�^�ŃT�[�o�ƃN���C�A���g�̗����𓮍삳���邱�Ƃ�z�肵�Ă��܂�����Alocalhost���w�肵�Ă��܂��B�ڑ��|�[�g�́A443�Ԃɐݒ肵�Ă��܂��B�T�[�o���Ɠ����ԍ��ł����443�ԂłȂ��Ă��ǂ��̂ł����A�ꕔ�̃|�[�g�ԍ��́A���ł�OS�ő��̋@�\�Ɏg�p����Ă��邱�Ƃ�����܂�����A�����ł͈�ʓI��SSL�ڑ��̃|�[�g�ԍ��ł���443�Ԃ�p���Ă��܂��B

		String trustStore = "SSLtest/client/clientTruststore";
		System.setProperty("javax.net.ssl.trustStore" , trustStore );
		System.setProperty("javax.net.ssl.trustStorePassword", "asdfghjkl");

�����̍s�ł́AtrustStore�̓ǂݍ��݂Ɠǂݏo���̂��߂̃p�X���[�h��ݒ肵�Ă��܂��B�{��́A"SSL"�f�B���N�g���Ŏ��s���邱�Ƃ�z�肵�Ă��܂��B�܂��AtrustStore�́A���΃p�X"SSLtest/client/clientTrustStore"�̈ʒu�ɕۑ����Ă���܂��B"asdfghjkl"�Ƃ����̂̓p�X���[�h�ł��B�e�X�g�p�ɗp�ӂ���clientTruststore�̃p�X���[�h�́A���̂悤�ȊȒP�ȃp�X���[�h��ݒ肵�܂������A�ʏ�͂����Ɠ���p�X���[�h���w�肵�Ă����Ɨǂ��ł��傤�B
�@���ɁASSLServer.java�ł��B

		String keyStore = "SSLtest/server/serverKeystore";

�Ƃ��������́AserverKeystore�̏ꏊ���w�肵�Ă��܂��B�p�X���[�h�̎w��́A

			char[] keystorePass = "lkjhgfdsa".toCharArray();
			ks.load( new FileInputStream( keyStore ) , keystorePass );

�ł��B�{��ł́AserverKeystore�̃p�X���[�h��"lkjhgfdsa"�Ƃ��܂����B

�@�ȏ�ŁA�����������܂����B�R���p�C�������v���O���������s���܂��傤�B�܂��́ASSLServer������s���܂��BSSLClient�́A���̎��ł��B�������ԈႦ��Ƃ��܂����삵�܂��񂩂�A���ӂ��Ă��������B

>java SSLtest.SSLServer
SSL�ڑ���ҋ@���Ă��܂��B

SSLServer�����s����ƁA�ҋ@��Ԃɓ����Ă��܂��̂ŁA�R�}���h�v�����v�g�������ЂƂ����グ��K�v������܂��B

>java SSLtest.SSLClient
Server����̃��b�Z�[�W�FHello Client

�Ƃ����悤�ɁA���s�ɐ�������ƃT�[�o�[����̃��b�Z�[�W���󂯎�邱�Ƃ��ł��܂��B����A�T�[�o�[���N�����Ă����R�}���h�v�����v�g�ɂ́A

SSL�ڑ���ҋ@���Ă��܂��B
Client����ڑ�����܂����B
Client����̃��b�Z�[�W:Hello Server
SSL�ڑ���ҋ@���Ă��܂��B

�Ƃ����悤�ȃ��b�Z�[�W���\������Ă��܂��B�N���C�A���g����������b�Z�[�W�𑗂�������ł��B�T�[�o�[�́A��������SSL�ʐM���󂯕t���邽�߂ɑҋ@���Ă��܂��B


�y�v���O�������z
-miniSSLc.java
-miniSSLs.java

�@miniSSLc.java�ɂ́A�N���C�A���g�������SSL�n���h�V�F�C�N�����Ⴊ�܂܂�Ă��܂��B�܂��AminiSSLs.java�ɂ́A�T�[�o�[�������SSL�n���h�V�F�C�N�����Ⴊ�܂܂�Ă��܂��B��̃v���O���������ꂼ��قȂ�R���s���[�^�Ŏ��s����΁A������T�[�o�[�A����������N���C�A���g�Ƃ���SSL�n���h�V�F�C�N���s����l�q���m�F���邱�Ƃ��ł��܂��B

[�R���p�C��]
�p�b�P�[�W��"SSL"�f�B���N�g���ŁA

>javac miniSSL\miniSSLc.java
>javac miniSSL\miniSSLs.java

�����s���邱�ƂŁA"miniSSL"�f�B���N�g���̉��ɁA"miniSSLc.class"��"miniSSLs.class"����������܂��B

[���s]
�{�v���O�������g�p����ɂ������āA�T�[�o�[�ؖ���serverKeyStore�ƃN���C�A���g�̎󂯓���\�ؖ������X�gclientTrustStore��p�ӂ��܂��B�쐬���@�́ASSLServer.java, SSLClient.java�̏Љ�̍ۂɏq�ׂ��̂ŁA���l�ɍ쐬���Ă��������B��̗�ō쐬�������̂����̂܂ܓ]�p���邱�Ƃ��ł��܂��B�{��ł́A�����̃t�@�C����"SSL/miniSSL"�f�B���N�g���ɒu���Ă��܂��B
�@�v���O�����̎��s�́A"SSL"�f�B���N�g���ōs���܂��B��ɁA�T�[�o�[�������s���܂��B

>java miniSSL.miniSSLs
Listening on port 443

�����ăN���C�A���g�������s���܂��B

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

�Ƃ����悤�ɕ\�������΁A�n���h�V�F�C�N�͐����ł��B�{�v���O�����́ASSL�n���h�V�F�C�N�̗����������邽�߂̂��̂ł��B��ʓI�Ȑڑ��ɑΉ����邱�Ƃ��ł���킯�ł͂���܂��񂩂�A�v���O�����̗����ǂ��āA�d�l���̓��e����ǂ���̂ɖ𗧂ĂĂ��������B

