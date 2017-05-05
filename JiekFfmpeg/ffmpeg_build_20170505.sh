#!/bin/bash
export TMPDIR=/Users/jiek/workspace/ffmpeg/tempdir
NDK=/Users/Shared/sdk/ndk-bundle
SYSROOT=$NDK/platforms/android-16/arch-arm/
TOOLCHAIN=/Users/Shared/sdk/ndk-bundle/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64
CPU=arm
PREFIX=/Users/jiek/workspace/ffmpeg/output
ADDI_CFLAGS="-marm"
function build_one
{
    ./configure \
        --prefix=$PREFIX \
        --enable-shared \
        --disable-static \
        --disable-doc \
        --disable-ffmpeg \
        --disable-ffplay \
        --disable-ffprobe \
        --disable-ffserver \
        --disable-doc \
        --disable-symver \
        --enable-small \
        --cross-prefix=$TOOLCHAIN/bin/arm-linux-androideabi- \
        --target-os=linux \
        --arch=arm \
        --enable-cross-compile \
        --sysroot=$SYSROOT \
        --extra-cflags="-Os -fpic $ADDI_CFLAGS" \
        --extra-ldflags="$ADDI_LDFLAGS" \
        $ADDITIONAL_CONFIGURE_FLAG

        sed -i '' 's/HAVE_LRINT 0/HAVE_LRINT 1/g' config.h
        sed -i '' 's/HAVE_LRINTF 0/HAVE_LRINTF 1/g' config.h
        sed -i '' 's/HAVE_ROUND 0/HAVE_ROUND 1/g' config.h
        sed -i '' 's/HAVE_ROUNDF 0/HAVE_ROUNDF 1/g' config.h
        sed -i '' 's/HAVE_TRUNC 0/HAVE_TRUNC 1/g' config.h
        sed -i '' 's/HAVE_TRUNCF 0/HAVE_TRUNCF 1/g' config.h
        sed -i '' 's/HAVE_CBRT 0/HAVE_CBRT 1/g' config.h
        sed -i '' 's/HAVE_RINT 0/HAVE_RINT 1/g' config.h
    make clean
    make -j4
    make install
}
build_one
