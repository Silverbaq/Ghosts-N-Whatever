#!/usr/bin/env python
# -*- coding: utf-8 -*-
# we use pypng
# https://pythonhosted.org/pypng/ex.html

import pyqrcode
import sys
from PIL import Image, ImageDraw, ImageFont

def genLocationQr( location, prepend="", path="tmp" ):
    filename = '%s/location-qr-%s.png'%(path, location)
    url = pyqrcode.create(prepend+location)
    url.png(filename, scale=8)
    return filename

if __name__ == "__main__":
    villagename = "Cisnadie"
    imagefile = genLocationQr( villagename , prepend="", path="tmp")

    # from PIL import Image
    # from PIL import ImageFont
    # from PIL import ImageDraw
    #
    # img = Image.open(imagefile)
    # draw = ImageDraw.Draw(img)
    # # font = ImageFont.truetype(<font-file>, <font-size>)
    # font = ImageFont.truetype("OpenSans-Regular.ttf", 16)
    # # draw.text((x, y),"Sample Text",(r,g,b))
    # #draw.text((0, 0),"Sample Text",(255,255,255),font=font)
    # draw.text((0, 0),"Sample Text",(255,255,255))
    # img.save('sample-out.jpg')

    # create canvas
    im_size = (500, 500)
    im = Image.new('RGBA', im_size, (255,255,255, 255))

    # load qr code½
    im_qrcode = Image.open( imagefile, 'r')
    im_qrcode_size = im_qrcode.size

    # add qr code to canvas
    offset = ((im_size[0] - im_qrcode_size[0]) / 2, 15)
    im.paste( im_qrcode, offset)

    draw = ImageDraw.Draw(im)
    font = ImageFont.truetype("OpenSans-Regular.ttf", 60)
    draw.text((80, 250),villagename,(0,0,0),font=font)

    # write to file
    im.save( "location-%s"%villagename, "PNG")
