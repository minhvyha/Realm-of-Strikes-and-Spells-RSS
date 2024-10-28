import os
from PIL import Image

# Set the quality of the output image (higher is better quality but larger file size)
quality = 80

# Set the directory where the images are located
input_dir = './src/assets/background/'
output_dir = './src/assets/background/'

# Create the output directory if it does not exist
os.makedirs(output_dir, exist_ok=True)

# Function to compress and resize images in all subdirectories
def process_images(input_folder, output_folder):
    for root, _, files in os.walk(input_folder):
        # Calculate relative path and corresponding output directory
        relative_path = os.path.relpath(root, input_folder)
        output_subdir = os.path.join(output_folder, relative_path)
        os.makedirs(output_subdir, exist_ok=True)
        
        for filename in files:
            # Check if the file is an image
            if filename.lower().endswith(('.jpg', '.jpeg', '.png')):
                # Open the image
                image_path = os.path.join(root, filename)
                image = Image.open(image_path)
                
                # Resize the image by 50%
                new_size = (image.width // 2, image.height // 2)
                image = image.resize(new_size, Image.LANCZOS)
                
                # Compress and save to the corresponding output directory
                output_path = os.path.join(output_subdir, filename)
                image.save(output_path, quality=quality, optimize=True)
                
    print('Compression and resizing by 50% completed for all images in directory and subdirectories!')

# Run the function
process_images(input_dir, output_dir)
