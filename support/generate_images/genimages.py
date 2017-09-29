#!/usr/bin/env python
# -*- coding: utf-8 -*-
# we use pypng
# https://pythonhosted.org/pypng/ex.html

import pyqrcode

def genLocationQr( location, prepend="", path="tmp" ):
    url = pyqrcode.create(prepend+location)
    url.png('%s/location-qr-%s.png'%(path, location), scale=8)


if __name__ == "__main__":
    villagename = "Cisnadie"
    genLocationQr( villagename , prepend="", path="tmp")
