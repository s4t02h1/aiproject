
package aes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import junit.framework.Assert;

import org.junit.Test;

public class AESAlgorithm {
    public AESAlgorithm() {
        super();
        this.state_ = null;
        this.roundkeys_ = null;
    }

    public void setBlock(byte[] buffer, int offset) {
        if (buffer == null || offset < 0 || buffer.length < offset + 16)
            throw new IllegalArgumentException();

        this.state_ = new int[4];
        for (int i = 0; i < 4; i++)
            this.state_[i] = buffer[offset + i * 4    ] & 0xff
                          | (buffer[offset + i * 4 + 1] & 0xff) <<  8
                          | (buffer[offset + i * 4 + 2] & 0xff) << 16
                          | (buffer[offset + i * 4 + 3] & 0xff) << 24;
    }

    public void getBlock(byte[] buffer, int offset) {
        if (buffer == null || offset < 0 || buffer.length < offset + 16)
            throw new IllegalArgumentException();

        for (int i = 0; i < 4; i++) {
            buffer[offset + i * 4    ] = (byte)  this.state_[i]       ;
            buffer[offset + i * 4 + 1] = (byte) (this.state_[i] >>  8);
            buffer[offset + i * 4 + 2] = (byte) (this.state_[i] >> 16);
            buffer[offset + i * 4 + 3] = (byte) (this.state_[i] >> 24);
        }
    }
    
    public void addRoundKey(int[] rk) {
        if (rk == null || rk.length != 4)
            throw new IllegalArgumentException();

        for (int i = 0; i < 4; i++)
            this.state_[i] ^= rk[i];
    }

    private int subWord(int a) {
        return  SBOX[a       & 0xff] & 0xff
             | (SBOX[a >>  8 & 0xff] & 0xff) <<  8
             | (SBOX[a >> 16 & 0xff] & 0xff) << 16
             | (SBOX[a >> 24 & 0xff] & 0xff) << 24;
    }

    private int invSubWord(int a) {
        return  INV_SBOX[a       & 0xff] & 0xff
             | (INV_SBOX[a >>  8 & 0xff] & 0xff) <<  8
             | (INV_SBOX[a >> 16 & 0xff] & 0xff) << 16
             | (INV_SBOX[a >> 24 & 0xff] & 0xff) << 24;
    }

    private void subBytes() {
        for (int i = 0; i < 4; i++)
            this.state_[i] = this.subWord(this.state_[i]);
    }

    private void invSubBytes() {
        for (int i = 0; i < 4; i++)
            this.state_[i] = this.invSubWord(this.state_[i]);
    }

    private void shiftRows() {
        int tmp = this.state_[0];
        this.state_[0] = (this.state_[0] & 0xffff00ff) | (this.state_[1] & 0x0000ff00);
        this.state_[1] = (this.state_[1] & 0xffff00ff) | (this.state_[2] & 0x0000ff00);
        this.state_[2] = (this.state_[2] & 0xffff00ff) | (this.state_[3] & 0x0000ff00);
        this.state_[3] = (this.state_[3] & 0xffff00ff) | (tmp            & 0x0000ff00);

        tmp = this.state_[0];
        this.state_[0] = (this.state_[0] & 0xff00ffff) | (this.state_[2] & 0x00ff0000);
        this.state_[2] = (this.state_[2] & 0xff00ffff) | (tmp            & 0x00ff0000);
        tmp = this.state_[1];
        this.state_[1] = (this.state_[1] & 0xff00ffff) | (this.state_[3] & 0x00ff0000);
        this.state_[3] = (this.state_[3] & 0xff00ffff) | (tmp            & 0x00ff0000);

        tmp = this.state_[0];
        this.state_[0] = (this.state_[0] & 0x00ffffff) | (this.state_[3] & 0xff000000);
        this.state_[3] = (this.state_[3] & 0x00ffffff) | (this.state_[2] & 0xff000000);
        this.state_[2] = (this.state_[2] & 0x00ffffff) | (this.state_[1] & 0xff000000);
        this.state_[1] = (this.state_[1] & 0x00ffffff) | (tmp            & 0xff000000);
    }
    
    private void invShiftRows() {
        int tmp = this.state_[0];
        this.state_[0] = (this.state_[0] & 0xffff00ff) | (this.state_[3] & 0x0000ff00);
        this.state_[3] = (this.state_[3] & 0xffff00ff) | (this.state_[2] & 0x0000ff00);
        this.state_[2] = (this.state_[2] & 0xffff00ff) | (this.state_[1] & 0x0000ff00);
        this.state_[1] = (this.state_[1] & 0xffff00ff) | (tmp            & 0x0000ff00);

        tmp = this.state_[0];
        this.state_[0] = (this.state_[0] & 0xff00ffff) | (this.state_[2] & 0x00ff0000);
        this.state_[2] = (this.state_[2] & 0xff00ffff) | (tmp            & 0x00ff0000);
        tmp = this.state_[1];
        this.state_[1] = (this.state_[1] & 0xff00ffff) | (this.state_[3] & 0x00ff0000);
        this.state_[3] = (this.state_[3] & 0xff00ffff) | (tmp            & 0x00ff0000);

        tmp = this.state_[0];
        this.state_[0] = (this.state_[0] & 0x00ffffff) | (this.state_[1] & 0xff000000);
        this.state_[1] = (this.state_[1] & 0x00ffffff) | (this.state_[2] & 0xff000000);
        this.state_[2] = (this.state_[2] & 0x00ffffff) | (this.state_[3] & 0xff000000);
        this.state_[3] = (this.state_[3] & 0x00ffffff) | (tmp            & 0xff000000);
    }

    private byte mul2_F2(byte a) {
        boolean msb = (a & 0x80) != 0;
        a <<= 1;
        if (msb)
            a ^= 0x11b;
        return a;
    }

    private byte mul_F2(byte a, byte b) {
        byte x = 0;
        for (int i = 0; i < 8; i++) {
            if ((b & 1) != 0)
                x ^= a;
            a = this.mul2_F2(a);
            b >>= 1;
        }
        return x;
    }

    private int mixColumn(int column) {
        byte o0 = (byte)  column;
        byte o1 = (byte) (column >>  8);
        byte o2 = (byte) (column >> 16);
        byte o3 = (byte) (column >> 24);

        byte t0 = this.mul2_F2(o0);
        byte t1 = this.mul2_F2(o1);
        byte t2 = this.mul2_F2(o2);
        byte t3 = this.mul2_F2(o3);

        return (t0       ^ o1 ^ t1 ^ o2      ^ o3     ) & 0xff
             | ((o0      ^ t1      ^ o2 ^ t2 ^ o3     ) & 0xff) <<  8
             | ((o0      ^ o1      ^ t2      ^ o3 ^ t3) & 0xff) << 16
             | ((o0 ^ t0 ^ o1      ^ o2      ^ t3     ) & 0xff) << 24;
    }
    
    private void mixColumns() {
        for (int i = 0; i < 4; i++)
            this.state_[i] = this.mixColumn(this.state_[i]);
    }

    private int invMixColumn(int column) {
        byte a0 = (byte)  column;
        byte a1 = (byte) (column >> 8);
        byte a2 = (byte) (column >> 16);
        byte a3 = (byte) (column >> 24);

        return ((this.mul_F2(a0, (byte) 14) ^ this.mul_F2(a1, (byte) 11) ^ this.mul_F2(a2, (byte) 13) ^ this.mul_F2(a3, (byte)  9)) & 0xff)
             | ((this.mul_F2(a0, (byte)  9) ^ this.mul_F2(a1, (byte) 14) ^ this.mul_F2(a2, (byte) 11) ^ this.mul_F2(a3, (byte) 13)) & 0xff) <<  8
             | ((this.mul_F2(a0, (byte) 13) ^ this.mul_F2(a1, (byte)  9) ^ this.mul_F2(a2, (byte) 14) ^ this.mul_F2(a3, (byte) 11)) & 0xff) << 16
             | ((this.mul_F2(a0, (byte) 11) ^ this.mul_F2(a1, (byte) 13) ^ this.mul_F2(a2, (byte)  9) ^ this.mul_F2(a3, (byte) 14)) & 0xff) << 24;
    }

    private void invMixColumns() {
        for (int i = 0; i < 4; i++)
            this.state_[i] = this.invMixColumn(this.state_[i]);
    }

    private int rotWord(int a) {
        return a >> 8 & 0x00ffffff | (a & 0xff) << 24;
    }

    private int[][] expandsKey(byte[] k) {
        int nk = k.length / 4; // key length
        int nr = nk + 6; // round count
        int[][] rk = new int[nr + 1][4]; // expanded key
        // setup initial round key
        for (int i = 0; i < nk; i++)
            rk[i >> 2][i & 3] =  k[i * 4    ] & 0xff
                              | (k[i * 4 + 1] & 0xff) <<  8
                              | (k[i * 4 + 2] & 0xff) << 16
                              | (k[i * 4 + 3] & 0xff) << 24;
        // calc. tail
        int x = rk[nk - 1 >> 2][nk - 1 & 3];
        for (int i = nk; i < (nr + 1) * 4; i++) {
            if (i % nk == 0)
                x = this.subWord(this.rotWord(x)) ^ RCON[i / nk];
            if (nk == 8 && i % nk == 4)
                x = this.subWord(x);
            x ^= rk[i - nk >> 2][i - nk & 3];
            rk[i >> 2][i & 3] = x;
        }
        return rk;
    }
    
    public void setKey(byte[] k) {
        if (k == null || (k.length != 16 && k.length != 24 && k.length != 32))
            throw new IllegalArgumentException();

        this.roundkeys_ = this.expandsKey(k);
    }

    public void encryptBlock() {
        this.addRoundKey(this.roundkeys_[0]);
        for (int i = 1; i < this.roundkeys_.length - 1; i++) {
            this.subBytes();
            this.shiftRows();
            this.mixColumns();
            this.addRoundKey(this.roundkeys_[i]);
        }
        this.subBytes();
        this.shiftRows();
        this.addRoundKey(this.roundkeys_[this.roundkeys_.length - 1]);
    }

    public void decryptBlock() {
        this.addRoundKey(this.roundkeys_[this.roundkeys_.length - 1]);
        this.invShiftRows();
        this.invSubBytes();
        for (int i = this.roundkeys_.length - 2; i >= 1; i--) {
            this.addRoundKey(this.roundkeys_[i]);
            this.invMixColumns();
            this.invShiftRows();
            this.invSubBytes();
        }
        this.addRoundKey(this.roundkeys_[0]);
    }

    private int[]               state_;

    private int[][]             roundkeys_;

    private static byte[]        SBOX     = {
        (byte) 0x63, (byte) 0x7c, (byte) 0x77, (byte) 0x7b, (byte) 0xf2, (byte) 0x6b, (byte) 0x6f, (byte) 0xc5, // 0
        (byte) 0x30, (byte) 0x01, (byte) 0x67, (byte) 0x2b, (byte) 0xfe, (byte) 0xd7, (byte) 0xab, (byte) 0x76,
        (byte) 0xca, (byte) 0x82, (byte) 0xc9, (byte) 0x7d, (byte) 0xfa, (byte) 0x59, (byte) 0x47, (byte) 0xf0, // 1
        (byte) 0xad, (byte) 0xd4, (byte) 0xa2, (byte) 0xaf, (byte) 0x9c, (byte) 0xa4, (byte) 0x72, (byte) 0xc0,
        (byte) 0xb7, (byte) 0xfd, (byte) 0x93, (byte) 0x26, (byte) 0x36, (byte) 0x3f, (byte) 0xf7, (byte) 0xcc, // 2
        (byte) 0x34, (byte) 0xa5, (byte) 0xe5, (byte) 0xf1, (byte) 0x71, (byte) 0xd8, (byte) 0x31, (byte) 0x15,
        (byte) 0x04, (byte) 0xc7, (byte) 0x23, (byte) 0xc3, (byte) 0x18, (byte) 0x96, (byte) 0x05, (byte) 0x9a, // 3
        (byte) 0x07, (byte) 0x12, (byte) 0x80, (byte) 0xe2, (byte) 0xeb, (byte) 0x27, (byte) 0xb2, (byte) 0x75,
        (byte) 0x09, (byte) 0x83, (byte) 0x2c, (byte) 0x1a, (byte) 0x1b, (byte) 0x6e, (byte) 0x5a, (byte) 0xa0, // 4
        (byte) 0x52, (byte) 0x3b, (byte) 0xd6, (byte) 0xb3, (byte) 0x29, (byte) 0xe3, (byte) 0x2f, (byte) 0x84,
        (byte) 0x53, (byte) 0xd1, (byte) 0x00, (byte) 0xed, (byte) 0x20, (byte) 0xfc, (byte) 0xb1, (byte) 0x5b, // 5
        (byte) 0x6a, (byte) 0xcb, (byte) 0xbe, (byte) 0x39, (byte) 0x4a, (byte) 0x4c, (byte) 0x58, (byte) 0xcf,
        (byte) 0xd0, (byte) 0xef, (byte) 0xaa, (byte) 0xfb, (byte) 0x43, (byte) 0x4d, (byte) 0x33, (byte) 0x85, // 6
        (byte) 0x45, (byte) 0xf9, (byte) 0x02, (byte) 0x7f, (byte) 0x50, (byte) 0x3c, (byte) 0x9f, (byte) 0xa8,
        (byte) 0x51, (byte) 0xa3, (byte) 0x40, (byte) 0x8f, (byte) 0x92, (byte) 0x9d, (byte) 0x38, (byte) 0xf5, // 7
        (byte) 0xbc, (byte) 0xb6, (byte) 0xda, (byte) 0x21, (byte) 0x10, (byte) 0xff, (byte) 0xf3, (byte) 0xd2,
        (byte) 0xcd, (byte) 0x0c, (byte) 0x13, (byte) 0xec, (byte) 0x5f, (byte) 0x97, (byte) 0x44, (byte) 0x17, // 8
        (byte) 0xc4, (byte) 0xa7, (byte) 0x7e, (byte) 0x3d, (byte) 0x64, (byte) 0x5d, (byte) 0x19, (byte) 0x73,
        (byte) 0x60, (byte) 0x81, (byte) 0x4f, (byte) 0xdc, (byte) 0x22, (byte) 0x2a, (byte) 0x90, (byte) 0x88, // 9
        (byte) 0x46, (byte) 0xee, (byte) 0xb8, (byte) 0x14, (byte) 0xde, (byte) 0x5e, (byte) 0x0b, (byte) 0xdb,
        (byte) 0xe0, (byte) 0x32, (byte) 0x3a, (byte) 0x0a, (byte) 0x49, (byte) 0x06, (byte) 0x24, (byte) 0x5c, // a
        (byte) 0xc2, (byte) 0xd3, (byte) 0xac, (byte) 0x62, (byte) 0x91, (byte) 0x95, (byte) 0xe4, (byte) 0x79,
        (byte) 0xe7, (byte) 0xc8, (byte) 0x37, (byte) 0x6d, (byte) 0x8d, (byte) 0xd5, (byte) 0x4e, (byte) 0xa9, // b
        (byte) 0x6c, (byte) 0x56, (byte) 0xf4, (byte) 0xea, (byte) 0x65, (byte) 0x7a, (byte) 0xae, (byte) 0x08,
        (byte) 0xba, (byte) 0x78, (byte) 0x25, (byte) 0x2e, (byte) 0x1c, (byte) 0xa6, (byte) 0xb4, (byte) 0xc6, // c
        (byte) 0xe8, (byte) 0xdd, (byte) 0x74, (byte) 0x1f, (byte) 0x4b, (byte) 0xbd, (byte) 0x8b, (byte) 0x8a,
        (byte) 0x70, (byte) 0x3e, (byte) 0xb5, (byte) 0x66, (byte) 0x48, (byte) 0x03, (byte) 0xf6, (byte) 0x0e, // d
        (byte) 0x61, (byte) 0x35, (byte) 0x57, (byte) 0xb9, (byte) 0x86, (byte) 0xc1, (byte) 0x1d, (byte) 0x9e,
        (byte) 0xe1, (byte) 0xf8, (byte) 0x98, (byte) 0x11, (byte) 0x69, (byte) 0xd9, (byte) 0x8e, (byte) 0x94, // e
        (byte) 0x9b, (byte) 0x1e, (byte) 0x87, (byte) 0xe9, (byte) 0xce, (byte) 0x55, (byte) 0x28, (byte) 0xdf,
        (byte) 0x8c, (byte) 0xa1, (byte) 0x89, (byte) 0x0d, (byte) 0xbf, (byte) 0xe6, (byte) 0x42, (byte) 0x68, // f
        (byte) 0x41, (byte) 0x99, (byte) 0x2d, (byte) 0x0f, (byte) 0xb0, (byte) 0x54, (byte) 0xbb, (byte) 0x16
    };
    
    private static final byte[] INV_SBOX = {
        (byte) 0x52, (byte) 0x09, (byte) 0x6a, (byte) 0xd5, (byte) 0x30, (byte) 0x36, (byte) 0xa5, (byte) 0x38, // 0
        (byte) 0xbf, (byte) 0x40, (byte) 0xa3, (byte) 0x9e, (byte) 0x81, (byte) 0xf3, (byte) 0xd7, (byte) 0xfb,
        (byte) 0x7c, (byte) 0xe3, (byte) 0x39, (byte) 0x82, (byte) 0x9b, (byte) 0x2f, (byte) 0xff, (byte) 0x87, // 1
        (byte) 0x34, (byte) 0x8e, (byte) 0x43, (byte) 0x44, (byte) 0xc4, (byte) 0xde, (byte) 0xe9, (byte) 0xcb,
        (byte) 0x54, (byte) 0x7b, (byte) 0x94, (byte) 0x32, (byte) 0xa6, (byte) 0xc2, (byte) 0x23, (byte) 0x3d, // 2
        (byte) 0xee, (byte) 0x4c, (byte) 0x95, (byte) 0x0b, (byte) 0x42, (byte) 0xfa, (byte) 0xc3, (byte) 0x4e,
        (byte) 0x08, (byte) 0x2e, (byte) 0xa1, (byte) 0x66, (byte) 0x28, (byte) 0xd9, (byte) 0x24, (byte) 0xb2, // 3
        (byte) 0x76, (byte) 0x5b, (byte) 0xa2, (byte) 0x49, (byte) 0x6d, (byte) 0x8b, (byte) 0xd1, (byte) 0x25,
        (byte) 0x72, (byte) 0xf8, (byte) 0xf6, (byte) 0x64, (byte) 0x86, (byte) 0x68, (byte) 0x98, (byte) 0x16, // 4
        (byte) 0xd4, (byte) 0xa4, (byte) 0x5c, (byte) 0xcc, (byte) 0x5d, (byte) 0x65, (byte) 0xb6, (byte) 0x92,
        (byte) 0x6c, (byte) 0x70, (byte) 0x48, (byte) 0x50, (byte) 0xfd, (byte) 0xed, (byte) 0xb9, (byte) 0xda, // 5
        (byte) 0x5e, (byte) 0x15, (byte) 0x46, (byte) 0x57, (byte) 0xa7, (byte) 0x8d, (byte) 0x9d, (byte) 0x84,
        (byte) 0x90, (byte) 0xd8, (byte) 0xab, (byte) 0x00, (byte) 0x8c, (byte) 0xbc, (byte) 0xd3, (byte) 0x0a, // 6
        (byte) 0xf7, (byte) 0xe4, (byte) 0x58, (byte) 0x05, (byte) 0xb8, (byte) 0xb3, (byte) 0x45, (byte) 0x06,
        (byte) 0xd0, (byte) 0x2c, (byte) 0x1e, (byte) 0x8f, (byte) 0xca, (byte) 0x3f, (byte) 0x0f, (byte) 0x02, // 7
        (byte) 0xc1, (byte) 0xaf, (byte) 0xbd, (byte) 0x03, (byte) 0x01, (byte) 0x13, (byte) 0x8a, (byte) 0x6b,
        (byte) 0x3a, (byte) 0x91, (byte) 0x11, (byte) 0x41, (byte) 0x4f, (byte) 0x67, (byte) 0xdc, (byte) 0xea, // 8
        (byte) 0x97, (byte) 0xf2, (byte) 0xcf, (byte) 0xce, (byte) 0xf0, (byte) 0xb4, (byte) 0xe6, (byte) 0x73,
        (byte) 0x96, (byte) 0xac, (byte) 0x74, (byte) 0x22, (byte) 0xe7, (byte) 0xad, (byte) 0x35, (byte) 0x85, // 9
        (byte) 0xe2, (byte) 0xf9, (byte) 0x37, (byte) 0xe8, (byte) 0x1c, (byte) 0x75, (byte) 0xdf, (byte) 0x6e,
        (byte) 0x47, (byte) 0xf1, (byte) 0x1a, (byte) 0x71, (byte) 0x1d, (byte) 0x29, (byte) 0xc5, (byte) 0x89, // a
        (byte) 0x6f, (byte) 0xb7, (byte) 0x62, (byte) 0x0e, (byte) 0xaa, (byte) 0x18, (byte) 0xbe, (byte) 0x1b,
        (byte) 0xfc, (byte) 0x56, (byte) 0x3e, (byte) 0x4b, (byte) 0xc6, (byte) 0xd2, (byte) 0x79, (byte) 0x20, // b
        (byte) 0x9a, (byte) 0xdb, (byte) 0xc0, (byte) 0xfe, (byte) 0x78, (byte) 0xcd, (byte) 0x5a, (byte) 0xf4,
        (byte) 0x1f, (byte) 0xdd, (byte) 0xa8, (byte) 0x33, (byte) 0x88, (byte) 0x07, (byte) 0xc7, (byte) 0x31, // c
        (byte) 0xb1, (byte) 0x12, (byte) 0x10, (byte) 0x59, (byte) 0x27, (byte) 0x80, (byte) 0xec, (byte) 0x5f,
        (byte) 0x60, (byte) 0x51, (byte) 0x7f, (byte) 0xa9, (byte) 0x19, (byte) 0xb5, (byte) 0x4a, (byte) 0x0d, // d
        (byte) 0x2d, (byte) 0xe5, (byte) 0x7a, (byte) 0x9f, (byte) 0x93, (byte) 0xc9, (byte) 0x9c, (byte) 0xef,
        (byte) 0xa0, (byte) 0xe0, (byte) 0x3b, (byte) 0x4d, (byte) 0xae, (byte) 0x2a, (byte) 0xf5, (byte) 0xb0, // e
        (byte) 0xc8, (byte) 0xeb, (byte) 0xbb, (byte) 0x3c, (byte) 0x83, (byte) 0x53, (byte) 0x99, (byte) 0x61,
        (byte) 0x17, (byte) 0x2b, (byte) 0x04, (byte) 0x7e, (byte) 0xba, (byte) 0x77, (byte) 0xd6, (byte) 0x26, // f
        (byte) 0xe1, (byte) 0x69, (byte) 0x14, (byte) 0x63, (byte) 0x55, (byte) 0x21, (byte) 0x0c, (byte) 0x7d
    };
    
    private static final int[]  RCON     = {
        0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c
    };
    
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

    @Test
    public void expandKeyTest192() {
        AESAlgorithm aes = new AESAlgorithm();

        byte[] key = new byte[24];
        for (int i = 0; i < 24; i++)
            key[i] = (byte) 0;
        int[][] roundKeys = aes.expandsKey(key);
        int[][] expectingKeys = new int[][] {
                new int[] { 0x00000000, 0x00000000, 0x00000000, 0x00000000 },
                new int[] { 0x00000000, 0x00000000, 0x62636363, 0x62636363 },
                new int[] { 0x62636363, 0x62636363, 0x62636363, 0x62636363 },
                new int[] { 0x9b9898c9, 0xf9fbfbaa, 0x9b9898c9, 0xf9fbfbaa },
                new int[] { 0x9b9898c9, 0xf9fbfbaa, 0x90973450, 0x696ccffa },
                new int[] { 0xf2f45733, 0x0b0fac99, 0x90973450, 0x696ccffa },
                new int[] { 0xc81d19a9, 0xa171d653, 0x53858160, 0x588a2df9 },
                new int[] { 0xc81d19a9, 0xa171d653, 0x7bebf49b, 0xda9a22c8 },
                new int[] { 0x891fa3a8, 0xd1958e51, 0x198897f8, 0xb8f941ab },
                new int[] { 0xc26896f7, 0x18f2b43f, 0x91ed1797, 0x407899c6 },
                new int[] { 0x59f00e3e, 0xe1094f95, 0x83ecbc0f, 0x9b1e0830 },
                new int[] { 0x0af31fa7, 0x4a8b8661, 0x137b885f, 0xf272c7ca },
                new int[] { 0x432ac886, 0xd834c0b6, 0xd2c7df11, 0x984c5970 } };

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

    @Test
    public void expandKeyTest256() {
        AESAlgorithm aes = new AESAlgorithm();

        byte[] key = new byte[32];
        for (int i = 0; i < 32; i++)
            key[i] = (byte) 0;
        int[][] roundKeys = aes.expandsKey(key);
        int[][] expectingKeys = new int[][] {
                new int[] { 0x00000000, 0x00000000, 0x00000000, 0x00000000 },
                new int[] { 0x00000000, 0x00000000, 0x00000000, 0x00000000 },
                new int[] { 0x62636363, 0x62636363, 0x62636363, 0x62636363 },
                new int[] { 0xaafbfbfb, 0xaafbfbfb, 0xaafbfbfb, 0xaafbfbfb },
                new int[] { 0x6f6c6ccf, 0x0d0f0fac, 0x6f6c6ccf, 0x0d0f0fac },
                new int[] { 0x7d8d8d6a, 0xd7767691, 0x7d8d8d6a, 0xd7767691 },
                new int[] { 0x5354edc1, 0x5e5be26d, 0x31378ea2, 0x3c38810e },
                new int[] { 0x968a81c1, 0x41fcf750, 0x3c717a3a, 0xeb070cab },
                new int[] { 0x9eaa8f28, 0xc0f16d45, 0xf1c6e3e7, 0xcdfe62e9 },
                new int[] { 0x2b312bdf, 0x6acddc8f, 0x56bca6b5, 0xbdbbaa1e },
                new int[] { 0x6406fd52, 0xa4f79017, 0x553173f0, 0x98cf1119 },
                new int[] { 0x6dbba90b, 0x07767584, 0x51cad331, 0xec71792f },
                new int[] { 0xe7b0e89c, 0x4347788b, 0x16760b7b, 0x8eb91a62 },
                new int[] { 0x74ed0ba1, 0x739b7e25, 0x2251ad14, 0xce20d43b },
                new int[] { 0x10f80a17, 0x53bf729c, 0x45c979e7, 0xcb706385 } };

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
    
    @Test
    public void mixColumnTest() {
        AESAlgorithm aes = new AESAlgorithm();
        Assert.assertEquals(aes.mixColumn(0x455313db), 0xbca14d8e);
        Assert.assertEquals(aes.mixColumn(0x5c220af2), 0x9d58dc9f);
        Assert.assertEquals(aes.mixColumn(0x01010101), 0x01010101);
        Assert.assertEquals(aes.mixColumn(0xc6c6c6c6), 0xc6c6c6c6);
        Assert.assertEquals(aes.mixColumn(0xd5d4d4d4), 0xd6d7d5d5);
        Assert.assertEquals(aes.mixColumn(0x4c31262d), 0xf8bd7e4d);
        Assert.assertEquals(aes.invMixColumn(0xbca14d8e), 0x455313db);
        Assert.assertEquals(aes.invMixColumn(0x9d58dc9f), 0x5c220af2);
        Assert.assertEquals(aes.invMixColumn(0x01010101), 0x01010101);
        Assert.assertEquals(aes.invMixColumn(0xc6c6c6c6), 0xc6c6c6c6);
        Assert.assertEquals(aes.invMixColumn(0xd6d7d5d5), 0xd5d4d4d4);
        Assert.assertEquals(aes.invMixColumn(0xf8bd7e4d), 0x4c31262d);
    }
    
    @Test
    public void encryptDecryptTest() {
        byte[] data = new byte[16];
        byte[] key = new byte[16];
        byte[] enc = new byte[16];
        byte[] dec = new byte[16];
        AESAlgorithm aes = new AESAlgorithm();

        for (int i = 0; i < 256; i++) {
            Random r = new Random(System.currentTimeMillis());
            r.nextBytes(key);
            aes.setKey(key);

            r.nextBytes(data);
            aes.setBlock(data, 0);
            aes.encryptBlock();
            aes.getBlock(enc, 0);

            aes.setBlock(enc, 0);
            aes.decryptBlock();
            aes.getBlock(dec, 0);

            for (int j = 0; j < 16; j++)
                Assert.assertEquals(data[j], dec[j]);
        }
    }
    
    @Test
    public void encryptTest() {
        byte[] data = new byte[16];
        byte[] key = new byte[16];
        byte[] enc1 = new byte[16];
        byte[] enc2 = null;
        AESAlgorithm aes = new AESAlgorithm();

        for (int i = 0; i < 256; i++) {
            Random r = new Random(System.currentTimeMillis());
            r.nextBytes(key);
            aes.setKey(key);

            r.nextBytes(data);
            aes.setBlock(data, 0);
            aes.encryptBlock();
            aes.getBlock(enc1, 0);

            SecretKeySpec sks = new SecretKeySpec(key, "AES");
            Cipher cipher;
            try {
                cipher = Cipher.getInstance("AES/ECB/NOPADDING", "SunJCE");
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                enc2 = cipher.doFinal(data);
            } catch (InvalidKeyException e) {
                Assert.fail();
            } catch (NoSuchAlgorithmException e) {
                Assert.fail();
            } catch (NoSuchPaddingException e) {
                Assert.fail();
            } catch (IllegalBlockSizeException e) {
                Assert.fail();
            } catch (BadPaddingException e) {
                Assert.fail();
            } catch (NoSuchProviderException e) {
                Assert.fail();
                e.printStackTrace();
            }

            Assert.assertEquals(enc1.length, 16);
            Assert.assertEquals(enc1.length, enc2.length);
            for (int j = 0; j < 16; j++)
                Assert.assertEquals(enc1[j], enc2[j]);
        }
    }
}
