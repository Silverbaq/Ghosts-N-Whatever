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

def genLocation( location, locationtype, prepend="", path="" ):
    imagefile = genLocationQr( location, prepend, path=path+"/tmp")

    # create canvas
    im_size = (500, 500)
    im = Image.new('RGBA', im_size, (255,255,255, 255))

    # load qr code½
    im_qrcode = Image.open( imagefile, 'r')
    im_qrcode_size = im_qrcode.size

    # add qr code to canvas
    offset = ((im_size[0] - im_qrcode_size[0]) / 2, 15)
    im.paste( im_qrcode, offset)

    # add text
    draw = ImageDraw.Draw(im)
    font = ImageFont.truetype("OpenSans-Regular.ttf", 40)
    draw.text((25, 400),prepend+location,(0,0,0),font=font)

    # write to file
    filename = ("%s/%s-%s.png"%(path, locationtype, location)).replace(' ', '-')
    im.save( filename, "PNG")
    return filename

def genCryptPic( location, locationtype, prepend="", path="" ):
    backgroundfile = "krypt.png"
    imagefile = genLocationQr( location, prepend, path=path+"/tmp")

    # create canvas
    # im_size = (500, 500)
    # im = Image.new('RGBA', im_size, (255,255,255, 255))
    im = Image.open( backgroundfile, 'r' )
    im_size = im.size
    print im_size

    # load qr code
    im_qrcode = Image.open( imagefile, 'r')
    maxsize = (400, 400)
    im_qr_sc = im_qrcode.resize(maxsize, Image.ANTIALIAS)
    im_qrcode_size = im_qr_sc.size

    # add qr code to canvas
    offset = ((im_size[0] - im_qrcode_size[0]), 0)
    im.paste( im_qr_sc , offset)

    # add text box
    text_box_size = (650, 150)
    print text_box_size
    im_white_box = Image.new('RGBA', text_box_size, (255,255,255,255))
    box_offset = ((im_size[0] - text_box_size[0]), (im_size[1] - text_box_size[1]))
    im.paste( im_white_box , box_offset)

    draw = ImageDraw.Draw(im)
    # loop to find biggest font
    for i in range(600):
        font = ImageFont.truetype("OpenSans-Regular.ttf", i)
        size = font.getsize(prepend+location)
        if (size[0] > 0.9*text_box_size[0]) or (size[1] > 0.9*text_box_size[1]):
            break
    box_centre = (im_size[0] - text_box_size[0]/2, im_size[1] - text_box_size[1]/2)
    print box_centre
    text_offset = ((box_centre[0]-size[0]/2), (box_centre[1]-size[1]/2))
    print text_offset
    draw.text(text_offset,prepend+location,(0,0,0),font=font)

    # write to file
    filename = ("%s/%s-%s.png"%(path, locationtype, location)).replace(' ', '-')
    im.save( filename, "PNG")
    return filename

if __name__ == "__main__":
    # with open( '../largest_towns_romania.txt', 'r') as list:
    #     for villagename in list:
    #         print "village names: ", villagename.strip()
    #         readable_name = villagename.split('\t')[1].strip()
    #         print " - using", readable_name
    #         fn = genLocation( readable_name, 'village', path='.' )
    #
    #         print "ristretto %s"%fn
    #

    with open( '../romanians_names_top_100.txt', 'r') as list:
        for surname in list:
            print "surname: ", surname.strip()
            fn = genCryptPic( surname.strip(), 'crypt', path='.' )

            #print "ristretto %s"%fn
